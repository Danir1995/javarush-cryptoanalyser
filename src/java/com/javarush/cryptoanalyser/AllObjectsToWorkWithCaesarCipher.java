package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AllObjectsToWorkWithCaesarCipher {
    public void callEncryptingFile() throws IOException, InvalidPathException {
        EncryptingFile cryptoLogic = new EncryptingFile();
        cryptoLogic.encryptFile(new Scanner(System.in));
    }
    public void callDecryptingFile() throws IOException, InputMismatchException {
        DecryptingFile hackingMessage = new DecryptingFile();
        hackingMessage.decryptFile(new Scanner(System.in));
    }
    public void callBruteForce() throws IOException{
        BruteForce bruteForce = new BruteForce();
        bruteForce.decryptFile();
    }
    public void callStaticAnalysis() throws IOException{
        StaticAnalysis staticAnalysis = new StaticAnalysis();
        staticAnalysis.checkSimilarText(new Scanner(System.in));
    }
}

