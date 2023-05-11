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

    public static Git init(File dir) throws Exception {
        return Git.init().setDirectory(dir).call();
    }

    public static void main(String[] args) throws Exception {
        Repository existingRepo = new FileRepositoryBuilder()
                .setGitDir(new File("C:\\Develop\\Projects\\new-dna\\.git"))
                .build();

        CredentialsProvider cp = new UsernamePasswordCredentialsProvider("taejinwoo", "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");
        Collection<Ref> remoteRefs = new Git(existingRepo).lsRemote()
                .setCredentialsProvider(cp)
                .setRemote("origin")
                .setTags(true)
                .setHeads(true)
                .call();
        for (Ref ref : remoteRefs) {
            System.out.println(ref.getName() + " -> " + ref.getObjectId().name());
        }
    }

}
