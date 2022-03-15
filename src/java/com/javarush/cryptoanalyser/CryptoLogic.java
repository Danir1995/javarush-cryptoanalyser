package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.channels.FileChannel;

public class CryptoLogic {

    private static final char[] ALPHABET = {
            'a','b','c','d','e','f','g','h','i','j','k','l',
            'm','n', 'o','p','q','r','s','t','u','v','w','x','y',
            'z','.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
             };

    public void encryption(){
        Scanner scanner = new Scanner(System.in);

        String originalFile = scanner.nextLine();
        String encryptedFile = scanner.nextLine();
        int key = scanner.nextInt();
        /*  C:/Windows/Tasks/Original.txt
            C:/Windows/Tasks/Encrypted.txt*/
        Path path1 = Path.of(originalFile);
        Path path2 = Path.of(encryptedFile);

        StringBuilder builder = new StringBuilder();
        if (Files.isRegularFile(path1) && Files.isRegularFile(path2)){
            try(Writer writer = new BufferedWriter(new FileWriter(encryptedFile))) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(originalFile, "rw");
                FileChannel channel = randomAccessFile.getChannel();

                ByteBuffer buffer = ByteBuffer.allocate(100);
                int bytesRead = channel.read(buffer);

                while (bytesRead != -1){
                    buffer.flip();
                    while (buffer.hasRemaining()){
                        builder.append((char) buffer.get());
                    }
                    buffer.clear();
                    bytesRead = channel.read(buffer);
                }
            //encrypting file
                for (int i = 0; i < builder.length(); i++){

                    for (int j = 0; j < ALPHABET.length; j++){

                        if ( ALPHABET[(char)j] == builder.charAt((char)i)){

                            if (j+key > ALPHABET.length){
                                writer.write(ALPHABET[(char)key - (ALPHABET.length - j)]);//от ключа отнять количество пройденных букв
                            }else {
                               writer.write(ALPHABET[(char)(j + key)]);
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
