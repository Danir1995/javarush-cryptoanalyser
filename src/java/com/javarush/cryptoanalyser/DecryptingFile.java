package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DecryptingFile extends EncryptingFile {

    public void decryptFile() throws IOException, InputMismatchException {
        addProhibitedFiles();

        Scanner s = ScannerSingleton.getInstance();
        System.out.println("Write encrypted file: ");
        String encryptedFile = s.nextLine();
        System.out.println("Write destination file to put decrypted message:");
        String originalFile = s.nextLine();
        System.out.println("Key: ");
        int key = s.nextInt();

        Path pathOfEncryptedFile = Path.of(encryptedFile);
        Path pathOfOriginalFile = Path.of(originalFile);

        for (String prohibited:prohibitedFiles) {
            if (originalFile.contains(prohibited) || encryptedFile.contains(prohibited)) {
                throw new RuntimeException("You want to change system file");
            }
        }

        if (!Files.isRegularFile(pathOfOriginalFile)){
            Files.createFile(pathOfOriginalFile);
        }

        mapping.clear();

        for (int i = 0; i < ALPHABET.length; i++) {
            if (key > ALPHABET.length) {
                mapping.put(ALPHABET[i], ALPHABET[((ALPHABET.length) + i - (key % ALPHABET.length)) % (ALPHABET.length)]);
            } else {
                mapping.put(ALPHABET[i], ALPHABET[((ALPHABET.length) + i - key) % (ALPHABET.length)]);
            }
        }

        if (Files.isRegularFile(pathOfEncryptedFile)){
            char[] data = Files.readString(pathOfEncryptedFile).toLowerCase().toCharArray();
            for (int i = 0; i < data.length; i++) {
                if(mapping.containsKey(data[i])) {
                    data[i] = mapping.get(data[i]);
                }
            }
            Files.writeString(pathOfOriginalFile, String.valueOf(data));
        }else {
            System.out.println("File name is incorrect or file doesn't exist");
            throw new RuntimeException("Please use correct name of file");
        }
    }
}
