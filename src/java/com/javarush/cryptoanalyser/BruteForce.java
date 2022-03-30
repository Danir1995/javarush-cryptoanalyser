package com.javarush.cryptoanalyser;

import java.io.IOException;

public class BruteForce extends DecryptingFile {

    public void decryptFile() throws IOException {
        super.decryptFile();
        FileIsDecrypted();
    }
    public void FileIsDecrypted() throws IOException {
        System.out.println("This file seems decrypted?");
        System.out.println("Press \"yes\" or \"no\": ");
        while (ScannerSingleton.getInstance().hasNextLine()) {
            String answer = ScannerSingleton.getInstance().nextLine();

            if ("no".equalsIgnoreCase(answer)) {
                decryptFile();

            } else if ("yes".equalsIgnoreCase(answer)) {
                System.out.println("You decrypted file!\nCongratulations!");
                break;
            }
        }
    }
}

