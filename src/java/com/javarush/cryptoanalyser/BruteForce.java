package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.util.Scanner;

public class BruteForce extends DecryptingFile {

    public void decryptFile() throws IOException {
        super.decryptFile(new Scanner(System.in));
        itIsOK();
    }
    public void itIsOK() throws IOException {

        System.out.println("This file seems decrypted?");
        System.out.println("Press \"yes\" or \"no\": ");

        Scanner scanner = new Scanner(System.in);


        while (true) {
            if(scanner.hasNextLine()) {

                String answer = scanner.nextLine();
                if ("no".equalsIgnoreCase(answer)) {
                    decryptFile();
                    break;

                } else if ("yes".equalsIgnoreCase(answer)) {
                    System.out.println("You decrypted file!\nCongratulations!");
                    break;
                }
            }
            System.out.println("please write \"yes\" or \"no\"");
        }
    }
}
