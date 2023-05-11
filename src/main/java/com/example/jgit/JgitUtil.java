package com.example.jgit;

import java.io.File;
import java.util.Collection;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;


public class JgitUtil {

    private static String userId = "qhrud7127";
    private static String userPass = "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";
    private static String userName = "kbk";
    private static String userEmail = "qhrud7127@naver.com";
    private static String hash = "origin/main";
    private static String url = "https://github.com/vtw-developers/git-test.git";
    private static CredentialsProvider cp = new UsernamePasswordCredentialsProvider(userId, userPass);

    public static Git init(File dir) throws Exception {
        return Git.init().setDirectory(dir).call();
    }

    public static void remoteAdd(Git git) throws Exception {
        // add remote repo:
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName("origin");
        remoteAddCommand.setUri(new URIish(url));
        // you can add more settings here if needed
        remoteAddCommand.call();
    }

    public static void push(Git git) throws Exception {
        // push to remote:
        PushCommand pushCommand = git.push();
        pushCommand.setCredentialsProvider(cp);
        pushCommand.setForce(true);
        // you can add more settings here if needed
        pushCommand.call();
    }

    public static void add(Git git, String filePattern) throws Exception {
        git.add().addFilepattern(filePattern).call();
    }

    public static void rm(Git git, String filePattern) throws Exception {
        git.rm().addFilepattern(filePattern).call();
    }

    public static void commit(Git git, String msg) throws Exception {
        // Now, we do the commit with a message
        git.commit()//
          .setAuthor(userName, userEmail)//
          .setMessage(msg)//
          .call();
    }

    public static void pull(Git git) throws Exception {
        PullCommand pull = git.pull();
        pull.call();
    }

    public static void lsRemote(Git git) throws Exception {
        Collection<Ref> remoteRefs = git.lsRemote().setCredentialsProvider(cp).setRemote("origin").setTags(false).setHeads(true).call();
        for (Ref ref : remoteRefs) {
            System.out.println(ref.getName() + " -> " + ref.getObjectId().name());
        }
    }

    public static void checkOut(File dir) throws Exception {
        Git gitRepo = Git.cloneRepository().setURI(url) // remote 주소
          .setDirectory(dir) // 다운받을 로컬의 위치
          .setNoCheckout(true)//
          .setCredentialsProvider(cp) // 인증 정보
          .call();
        gitRepo.checkout()
          .setStartPoint(hash) // origin/branch_name
          .setAllPaths(true)
          // .addPath("not thing") // 다운받을 대상 경로
          .call();

        gitRepo.getRepository().close();
    }

    public static Git open(File dir) throws Exception {
        Git git = null;
        try {
            git = Git.open(dir);
        } catch (RepositoryNotFoundException e) {
            git = JgitUtil.init(dir);
        }
        return git;
    }
}