package com.example.jgit;

import org.eclipse.jgit.api.Git;

import java.io.File;

public class GitInit {

    public static Git init(File dir) throws Exception {
        return Git.init().setDirectory(dir).call();
    }

    public static void main(String[] args) throws Exception {
        String dir = "C:/Develop/abcde";
        init(new File(dir));
    }

}
