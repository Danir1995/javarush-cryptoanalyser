package com.javarush.cryptoanalyser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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

        Path path1 = Path.of(originalFile);//C:/Windows/Tasks/Original.txt
        Path path2 = Path.of(encryptedFile);//C:/Windows/Tasks/Encrypted.txt


        if (Files.isRegularFile(path1) && Files.isRegularFile(path2)){
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(originalFile, "rw");
                FileChannel channel = randomAccessFile.getChannel();

                ByteBuffer buffer = ByteBuffer.allocate(100);
                int bytesRead = channel.read(buffer);
                StringBuilder builder = new StringBuilder();
                while (bytesRead != -1){
                    buffer.flip();
                    while (buffer.hasRemaining()){
                        builder.append((char) buffer.get());
                    }
                    buffer.clear();
                    bytesRead = channel.read(buffer);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("File name is incorrect or file doesn't exist");
        }
    }
}
