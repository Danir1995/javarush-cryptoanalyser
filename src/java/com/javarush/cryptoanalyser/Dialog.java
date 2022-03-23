package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Dialog {
    public static final String CANCEL = "cancel";

    void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Hello dear user!");
            System.out.println("Choose an option of the program: ");
            System.out.println("1: Encrypt file.");
            System.out.println("2: Decrypt file with");
            System.out.println("3: Decrypt file with brute - force.");
            System.out.println("4: Decrypt file with static analysis.");
            System.out.println("For cancel - use command: \"CANCEL\"");
            try {

                String numberOfFunction = scanner.nextLine();

                if ((numberOfFunction).equalsIgnoreCase(CANCEL)) {
                    System.out.println("closed");
                    return;
                }

                switch (Integer.parseInt(numberOfFunction)) {

                    case 1 -> {
                        CryptoLogic cryptoLogic = new CryptoLogic();
                        cryptoLogic.encryption();
                    }
                    case 2 -> {
                        HackingMessage hackingMessage = new HackingMessage();
                        hackingMessage.unblock();
                    }
                    case 3 -> {
                        BruteForce bruteForce = new BruteForce();
                        bruteForce.unblock();
                    }
                    case 4 -> {
                        StaticAnalysis staticAnalysis = new StaticAnalysis();
                        staticAnalysis.checkSimilarText();
                    }
                }
            } catch (NumberFormatException e){
                System.out.println("You used wrong command");
            }catch (InvalidPathException a){
                System.out.println("Problem with fileName, show it to your manager");
                System.out.println(a);
            }
            catch (IOException a) {
                System.out.println("Some problem, please show it to your manager");
                System.out.println( a);
            }
        }
    }
}
