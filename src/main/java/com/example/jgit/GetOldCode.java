package com.example.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GetOldCode {

    static String dir = "C:/Develop/Projects/new-dna/.git";
    static String commitId = "ff1628c8cdb7ae826617b0d9ad45a4338a22f074";
    static String path = "src/main/resources/application.yml";

    public static void main(String[] args) throws Exception {
        Repository repository = FileRepositoryBuilder.create(new File(dir));

        RevWalk revWalk = new RevWalk(repository);
        ObjectId head = repository.resolve(commitId);
        RevCommit commit = revWalk.parseCommit(head);

        RevTree tree = commit.getTree();
        TreeWalk treeWalk = new TreeWalk(repository);
        treeWalk.addTree(tree);
        treeWalk.setRecursive(true);
        treeWalk.setFilter(PathFilter.create(path));
        if (!treeWalk.next()) {
            return;
        }
        ObjectId objectId = treeWalk.getObjectId(0);
        ObjectLoader loader = repository.open(objectId);

        InputStream in = loader.openStream();
        String text = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(text);
    }

}
