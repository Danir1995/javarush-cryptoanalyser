package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BruteForce extends HackingMessage{

    @Override
    public void unblock() throws IOException {
        super.unblock();
        itIsOK();
    }
    public void itIsOK() throws IOException {

        System.out.println("This file seems good?");
        System.out.println("Press \"yes\" or \"no\": ");

        Scanner scanner = new Scanner(System.in);


        while (true) {
            if(scanner.hasNextLine()) {

                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("no")) {
                    unblock();
                    break;

                } else if (answer.equalsIgnoreCase("yes")) {
                    System.out.println("You decrypted file!\nCongratulations!");
                    break;
                }
            }
            System.out.println("please write \"yes\" or \"no\"");
        }
    }
}
