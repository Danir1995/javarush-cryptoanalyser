package com.javarush.cryptoanalyser;

import java.util.Scanner;

public class ScannerSingleton {

    private static final Scanner scannerInstance = new Scanner(System.in);

    private ScannerSingleton()
    {
    }

    public static Scanner getInstance() {
        return scannerInstance;
    }
}
