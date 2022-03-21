package com.javarush.cryptoanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

public class BruteForce extends HackingMessage{

    @Override
    public void unblock() {
        super.unblock();
        itIsOK();
    }
    public void itIsOK(){

        System.out.println("This file seems good?");
        System.out.println("Press \"yes\" or \"no\": ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        while (true) {
            if (answer.equalsIgnoreCase("no")) {
                unblock();
                break;
            } else if (answer.equalsIgnoreCase("yes")) {
                System.out.println("You decrypted file!\nCongratulations!");
                break;
            } else {
                System.out.println("Unrecognized command please try again in 5 minutes");
                break;
            }
        }

    }

}
