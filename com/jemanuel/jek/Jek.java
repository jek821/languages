package com.jemanuel.jek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


// Error Codes are based on freebsd sysexits.h "standard"
// https://man.freebsd.org/cgi/man.cgi?query=sysexits&apropos=0&sektion=0&manpath=FreeBSD+4.3-RELEASE&format=html

public class Jek {
    static boolean hadError = false;

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


    // Take in file path, use Paths to get system path, then use Files.read... to get all bytes
    private static void runFile(String path) throws IOException{
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    // Turn file bytes into a String object and pass to run()
    run(new String(bytes, Charset.defaultCharset()));

    if (hadError) System.exit(65);
    }

    // Function to run repl (no file passed in, so instead user can just enter lines to execute)
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (true) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            run(line);
            hadError = false;

        }
    }

    // Now we have the two wrapper functions for our run() method, one for using it on a files bytes
    // and another for use in a repl
    // Now lets actually write the run function that both of the previous methods will call

    private static void run(String source) {
        // initialize our scanner, and pass in the source code string, then use scanTokens()
        // to return the tokens from the source code
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // print out the tokens returned by the Scanner
        for (Token token : tokens){
            System.out.println(token);
        }
    }

    // Some basic error handling logic for reporting and then printing reported errors
    // with a bit of context

    static void error(int Line, String message){
        report(line, message);
    }

    private static void report(int line, String message) {
        System.err.println("[line " + line + "] ERROR: " + message);
        hadError = true;
    }



}
