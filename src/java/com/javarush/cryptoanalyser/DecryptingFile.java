package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class DecryptingFile extends EncryptingFile {

    public void decryptFile() throws IOException, InputMismatchException {

        addProhibitedFiles();

        Scanner scanner = new Scanner(System.in);
            System.out.println("Write encrypted file: ");

                String encryptedFile = scanner.nextLine();

            System.out.println("Write destination file to put decrypted message");
            String originalFile = scanner.nextLine();
        System.out.println("Key: ");
            int key = scanner.nextInt();


        Path pathOfOriginalFile = Path.of(originalFile);
        Path pathOfEncryptedFile = Path.of(encryptedFile);

        for (String prohibited:prohibitedFiles){
            if (originalFile.contains(prohibited) || encryptedFile.contains(prohibited)) {

                throw new RuntimeException("You want to change system file");

            }
        }

        StringBuilder builder = new StringBuilder();

        if (Files.isRegularFile(pathOfEncryptedFile)){
            try(Writer writer = new BufferedWriter(new FileWriter(originalFile));
               BufferedReader reader = new BufferedReader(new FileReader(encryptedFile))) {
              while (reader.ready()){
                  builder.append(reader.readLine());
              }
                if (!Files.isRegularFile(pathOfOriginalFile)){
                    Files.createFile(pathOfOriginalFile);
                }

                int countOfSteps = key - ((key / (ALPHABET.length)) * (ALPHABET.length));
                
                for (int i = 0; i < builder.length(); i++){
                    for (int j = 0; j < ALPHABET.length; j++){
                        if ( ALPHABET[(char)j] == builder.toString().toLowerCase(Locale.ROOT).charAt((char)i)){

                                if (j - countOfSteps < 0) {
                                    writer.write(ALPHABET[(ALPHABET.length - 1) - (countOfSteps - (j + 1))]);
                                }else if (j - countOfSteps == 0){
                                    writer.write(ALPHABET[0]);
                                } else {
                                    writer.write(ALPHABET[(char) (j - countOfSteps)]);
                                }
                            }
                        }
                    }
            }
        }else {
            System.out.println("File name is incorrect or file doesn't exist");
            throw new RuntimeException("Please use correct name of file");
        }
        System.out.println("You decrypted file");

    }
}
