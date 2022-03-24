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

    static int count = 0;
    public void checkSimilarText(Scanner scanner) throws IOException {

        System.out.println("Lets check a different book of the same author and discover the most usable letters." +
                "\nJust put the way to file:");

        scanner = ScannerSingleton.getInstance();

        String anotherText = scanner.nextLine();
        Path pathOfAnotherText = Path.of(anotherText);
        StringBuilder builder = new StringBuilder();

        if (Files.isRegularFile(pathOfAnotherText)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(pathOfAnotherText.toString()))) {

                while (reader.ready()) {
                    builder.append(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < builder.length(); i++) {
               Character key = builder.toString().toLowerCase().charAt((char)i);
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
                if (value > count){
                    count = value;
                }
                sortedLetters.put(value, key);
            }
            System.out.println("The most usable letter is: \"" + sortedLetters.get(count) + "\"\nIt was used: " + count + " times.");
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
        Scanner scanner = new Scanner(System.in);

        String anotherText = scanner.nextLine();
        Path path1 = Path.of(anotherText);

        StringBuilder builder = new StringBuilder();
        int count = 0;
        if (Files.isRegularFile(path1)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path1.toString()))) {

                while (reader.ready()) {
                    builder.append(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < builder.length(); i++) {
                Character key = builder.toString().toLowerCase().charAt((char)i);
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
                if (value > count){
                    count = value;
                }
                sortedLetters.put(value, key);
            }
            System.out.println("The most usable letter is: \"" + sortedLetters.get(count) + "\"\nIt was used: " + count + " times.");
        }else {
            System.out.println("File name is incorrect or file doesn't exist!");
            return;
        }
        char mostUsable = sortedLetters.get(count);
        int counterOfIndex = 0;
        while (ALPHABET[counterOfIndex] != mostUsable){
            counterOfIndex++;
        }
        System.out.println("Static analysis finished! \nYou have to use key: " + ((ALPHABET.length) - ((ALPHABET.length - 1) - counterOfIndex)));
            DecryptingFile hackingMessage = new DecryptingFile();
            hackingMessage.decryptFile();
    }
}
