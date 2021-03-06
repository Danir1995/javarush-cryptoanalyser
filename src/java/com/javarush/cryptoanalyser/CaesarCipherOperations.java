package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.InputMismatchException;

public class CaesarCipherOperations extends EncryptingFile{
    public void callEncryptingFile() throws IOException, InvalidPathException {
        EncryptingFile cryptoLogic = new EncryptingFile();
        cryptoLogic.encryptFile(ScannerSingleton.getInstance());
    }
    public void callDecryptingFile() throws IOException, InputMismatchException {
        DecryptingFile hackingMessage = new DecryptingFile();
        hackingMessage.decryptFile();
    }
    public void callBruteForce() throws IOException{
        BruteForce bruteForce = new BruteForce();
        bruteForce.decryptFile();
    }
    public void callStaticAnalysis() throws IOException{
        StaticAnalysis staticAnalysis = new StaticAnalysis();
        staticAnalysis.checkSimilarText();
    }
}

