package com.jemanuel.jek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Jek {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jek [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }

    }

}
