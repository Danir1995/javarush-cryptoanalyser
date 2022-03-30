package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StaticAnalysis extends DecryptingFile {

   public static TreeMap<Character, Integer> letters = new TreeMap<>();
   public static TreeMap<Integer, Character> sortedLetters = new TreeMap<>();
   static int counterOfLetters = 0;

   public void checkSimilarText() throws IOException {
       System.out.println("Lets check a different book of the same author and discover the most usable letters." +
                "\nJust put the way to file:");
       Scanner scanner = ScannerSingleton.getInstance();
       String anotherText = scanner.nextLine();
       Path pathOfAnotherText = Path.of(anotherText);

       StringBuilder charsOfFile = new StringBuilder();

       if (Files.isRegularFile(pathOfAnotherText)) {
           BufferedReader reader = new BufferedReader(new FileReader(anotherText));
                while (reader.ready()) {
                    charsOfFile.append(reader.readLine());
                }
           for (int i = 0; i < charsOfFile.length(); i++) {
               Character key = charsOfFile.toString().toLowerCase().charAt((char)i);
               if (letters.get(key) == null){
                   letters.put(key, 1);
               }else {
                   int value = letters.get(key);
                   value++;
                   letters.put(key, value);
               }
           }

           for(Map.Entry<Character, Integer> entry : letters.entrySet()) {
               Character key = entry.getKey();
               Integer value = entry.getValue();
               if (value > counterOfLetters){
                    counterOfLetters = value;
               }
               sortedLetters.put(value, key);
           }
           System.out.println("The most usable letter is: \"" + sortedLetters.get(counterOfLetters) + "\"\nIt was used: " + counterOfLetters + " times.");
       }else {
           System.out.println("File name is incorrect or file doesn't exist!");
           return;
       }
       checkEncryptedText();
    }

    public void checkEncryptedText() throws IOException {
       letters.clear();
       sortedLetters.clear();

       System.out.println("Now let's check encrypted file and discover the most usable letter.");
       Scanner scanner = ScannerSingleton.getInstance();
       String anotherText = scanner.nextLine();
       Path pathOfEncryptedText = Path.of(anotherText);

       StringBuilder charsOfEncryptedText = new StringBuilder();

       counterOfLetters = 0;

       if (Files.isRegularFile(pathOfEncryptedText)) {
           BufferedReader reader = new BufferedReader(new FileReader(pathOfEncryptedText.toString()));

           while (reader.ready()) {
               charsOfEncryptedText.append(reader.readLine());
           }

           for (int i = 0; i < charsOfEncryptedText.length(); i++) {
               Character key = charsOfEncryptedText.toString().toLowerCase().charAt((char)i);

               if (letters.get(key) == null){
                   letters.put(key, 1);
               }else {
                   int value = letters.get(key);
                   value++;
                   letters.put(key, value);
               }
           }

           for(Map.Entry<Character, Integer> entriesOfCharactersFromOriginalFile : letters.entrySet()) {
               Character key = entriesOfCharactersFromOriginalFile.getKey();
               Integer value = entriesOfCharactersFromOriginalFile.getValue();

               if (value > counterOfLetters){
                   counterOfLetters = value;
               }

               sortedLetters.put(value, key);
           }
           System.out.println("The most usable letter is: \"" + sortedLetters.get(counterOfLetters) + "\"\nIt was used: " + counterOfLetters + " times.");
        }else {
           System.out.println("File name is incorrect or file doesn't exist!");
           return;
        }

        char mostUsable = sortedLetters.get(counterOfLetters);
        int shift = 0;
        while (ALPHABET[shift] != mostUsable){
            shift++;
        }

        System.out.println("Static analysis finished! \nYou have to use key: " + ((ALPHABET.length) - ((ALPHABET.length - 1) - shift)));
        decryptFile();
    }
}
