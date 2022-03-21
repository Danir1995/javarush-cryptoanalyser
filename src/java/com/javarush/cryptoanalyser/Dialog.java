package com.javarush.cryptoanalyser;

import java.util.Scanner;

public class Dialog {
    void start(){
        Scanner scanner = new Scanner(System.in);
        int numberOfFunction;
        System.out.println("Hello dear user!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Choose an option of the program: ");
        System.out.println("1: Encrypt file.");
        System.out.println("2: Decrypt file.");
        System.out.println("3: Decrypt file with brute - force");
        System.out.println("4: Decrypt file with static analysis");

        numberOfFunction = scanner.nextInt();


        switch (numberOfFunction) {
            case 1 -> {
                CryptoLogic cryptoLogic = new CryptoLogic();
                cryptoLogic.encryption();
            }
            case 2 -> {
                HackingMessage hackingMessage = new HackingMessage();
                hackingMessage.unblock();
            }
        }
    }
}
