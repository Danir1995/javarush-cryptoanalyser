package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Dialogue {

    private static final int ENCRYPT_FILE = 1;
    private static final int DECRYPT_FILE = 2;
    private static final int BRUTE_FORCE = 3;
    private static final int STATIC_ANALYSIS = 4;
    private static final String CANCEL_FROM_GAME = "cancel";

    void start(Scanner scannerSingleton) {

        try {
            System.out.println();
            System.out.println("Hello dear user!");
            System.out.println("Choose an option of the program: ");
            System.out.println("1: Encrypt file.");
            System.out.println("2: Decrypt file with key.");
            System.out.println("3: Decrypt file with brute - force.");
            System.out.println("4: Decrypt file with static analysis.");
            System.out.println("For cancel - use command: \"CANCEL\"");

            String numberOfFunction = scannerSingleton.nextLine();
            if ((CANCEL_FROM_GAME).equalsIgnoreCase(numberOfFunction)) {
                System.out.println("closed");
                return;
            }

            CaesarCipherOperations caesarCipherOperations = new CaesarCipherOperations();
            switch (Integer.parseInt(numberOfFunction)) {
                case ENCRYPT_FILE -> caesarCipherOperations.callEncryptingFile();
                case DECRYPT_FILE -> caesarCipherOperations.callDecryptingFile();
                case BRUTE_FORCE -> caesarCipherOperations.callBruteForce();
                case STATIC_ANALYSIS -> caesarCipherOperations.callStaticAnalysis();
            }
        } catch (NumberFormatException e){
            System.out.println("You used wrong command");
            System.out.println(e);
        } catch (InvalidPathException a){
            System.out.println("Problem with fileName, show it to your manager");
            System.out.println(a);
        } catch (IOException a) {
            System.out.println("Some problem, please show it to your manager");
            System.out.println( a);
        } catch (InputMismatchException e){
            System.out.println("Your command doesn't match, please use correct command");
        }
        start(scannerSingleton);
    }
}
