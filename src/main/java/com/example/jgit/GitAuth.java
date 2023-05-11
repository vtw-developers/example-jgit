package com.example.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.util.Collection;

public class GitAuth {
    public static void main(String[] args) throws Exception {

        File dir = new File("C:\\git-test");
        JgitUtil.checkOut(dir);
        Git git = JgitUtil.open(dir);
        JgitUtil.remoteAdd(git);
//        JgitUtil.pull(git);
//        JgitUtil.add(git, "404.md");
//        JgitUtil.rm(git, "ReadMe.md");
//        JgitUtil.commit(git, "8888");
//        JgitUtil.push(git);
    }

}
