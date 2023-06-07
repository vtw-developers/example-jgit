package com.example.jgit;

import org.eclipse.jgit.api.Git;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativePath {

    public static void main(String[] args) throws Exception {
        Path absolutePath1 = Paths.get("C:\\Develop\\Projects\\new-dna\\build.gradle");
        Path absolutePath2 = Paths.get("C:\\Develop\\Projects");

        // convert the absolute path to relative path
        Path relativePath = absolutePath2.relativize(absolutePath1);
        System.out.println("Relative Path: " + relativePath);
    }

}
