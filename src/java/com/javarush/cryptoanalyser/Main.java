package com.javarush.cryptoanalyser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Dialogue dialogue = new Dialogue();
       dialogue.start(ScannerSingleton.getInstance());
  }
}
