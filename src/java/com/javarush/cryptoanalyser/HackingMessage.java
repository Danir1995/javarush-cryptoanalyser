package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class HackingMessage extends CryptoLogic{
    private static final char[] ALPHABET = {
            'a','b','c','d','e','f','g','h','i','j','k','l',
            'm','n', 'o','p','q','r','s','t','u','v','w','x','y',
            'z','.', ',', '"', '\'', ':', '!', '?', ' '
    };

    public void unblock(){
        addProhibitedFiles();
        Scanner scanner = new Scanner(System.in);

        String encryptedFile = scanner.nextLine();
        String originalFile = scanner.nextLine();

        int key = scanner.nextInt();

        Path path1 = Path.of(originalFile);
        Path path2 = Path.of(encryptedFile);
        for (String prohibited:prohibitedFiles){
            if (originalFile.contains(prohibited) || encryptedFile.contains(prohibited)) {
                System.out.println("You want to change system file");
                return;
            }}
        StringBuilder builder = new StringBuilder();
        if (Files.isRegularFile(path1)){
            try(Writer writer = new BufferedWriter(new FileWriter(originalFile));
               BufferedReader reader = new BufferedReader(new FileReader(encryptedFile))) {
              while (reader.ready()){
                  builder.append(reader.readLine());
              }
                if (!Files.isRegularFile(path1)){
                    Files.createFile(path1);
                }



                int i1 =(int) key - ((key / (ALPHABET.length)) * (ALPHABET.length));
                for (int i = 0; i < builder.length(); i++){
                    for (int j = 0; j < ALPHABET.length; j++){
                        if ( ALPHABET[(char)j] == builder.toString().toLowerCase(Locale.ROOT).charAt((char)i)){

                            if (key >= ALPHABET.length) {
                                if (j - i1 < 0) {
                                    writer.write(ALPHABET[(ALPHABET.length-1) - (i1-(j+1))]);
                                }else if (j - i1 == 0){
                                    writer.write(ALPHABET[0]);
                                } else {
                                    writer.write(ALPHABET[(char) (j - i1)]);
                                }
                            } else {
                                if ( ALPHABET[(char)j] == builder.toString().toLowerCase(Locale.ROOT).charAt((char)i)){
                                    if (j-key < 0){
                                        writer.write(ALPHABET[(ALPHABET.length-1) - (key-(j+1))]);
                                    }else if (j - key == 0){
                                        writer.write(ALPHABET[0]);
                                    }else  if(j - key>0){
                                        writer.write(ALPHABET[(char)(j - key)]);
                                    }
                                }
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("File name is incorrect or file doesn't exist");
        }

    }
}
