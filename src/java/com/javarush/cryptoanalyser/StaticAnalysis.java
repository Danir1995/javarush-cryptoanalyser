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

    static int counterOfCharacters = 0;

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
                if (value > counterOfCharacters){
                    counterOfCharacters = value;
                }
                sortedLetters.put(value, key);
            }
            System.out.println("The most usable letter is: \"" + sortedLetters.get(counterOfCharacters) + "\"\nIt was used: " + counterOfCharacters + " times.");
        }else {
            System.out.println("File name is incorrect or file doesn't exist!");
            return;
        }
        checkEncryptedText(new Scanner(System.in));
    }

    public void checkEncryptedText(Scanner scanner) throws IOException {

        letters.clear();
        sortedLetters.clear();

        System.out.println("Now let's check encrypted file and discover the most usable letter.");
        scanner = ScannerSingleton.getInstance();

        String anotherText = scanner.nextLine();
        Path pathOfEncryptedText = Path.of(anotherText);

        StringBuilder builder = new StringBuilder();

        counterOfCharacters = 0;

        if (Files.isRegularFile(pathOfEncryptedText)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(pathOfEncryptedText.toString()))) {

                while (reader.ready()) {
                    builder.append(reader.readLine());
                }
            } catch (IOException e) {
                System.out.println("Some problem, please try in 5 minutes");
                System.out.println(e.getStackTrace());
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

            for(Map.Entry<Character, Integer> entriesOfCharactersFromOriginalFile : letters.entrySet()) {
                Character key = entriesOfCharactersFromOriginalFile.getKey();
                Integer value = entriesOfCharactersFromOriginalFile.getValue();

                if (value > StaticAnalysis.counterOfCharacters){
                    StaticAnalysis.counterOfCharacters = value;
                }

                sortedLetters.put(value, key);
            }

            System.out.println("The most usable letter is: \"" + sortedLetters.get(StaticAnalysis.counterOfCharacters) + "\"\nIt was used: " + StaticAnalysis.counterOfCharacters + " times.");
        }else {
            System.out.println("File name is incorrect or file doesn't exist!");
            return;
        }

        char mostUsable = sortedLetters.get(StaticAnalysis.counterOfCharacters);

        int counterOfIndexFromAlphabet = 0;

        while (ALPHABET[counterOfIndexFromAlphabet] != mostUsable){
            counterOfIndexFromAlphabet++;
        }

        System.out.println("Static analysis finished! \nYou have to use key: " + ((ALPHABET.length) - ((ALPHABET.length - 1) - counterOfIndexFromAlphabet)));

            decryptFile();
    }
}
