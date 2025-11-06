import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;

public class WordValidation implements SpellingOperations {
    public HashSet<String> validWords = null;
    /**
     * Constructor 
     * @param dictionary a string that contains the list of valid words
     */
    public WordValidation(String dictionary) {
        // makes an empty hashSet from the file reading
        HashSet<String> validWords = new HashSet<String>();
        // reads string file
        String filename = (dictionary.length() > 0) ? dictionary : "words.txt";
        Scanner reader = null;

        try {
            reader = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
        while(reader.hasNextLine()) {
            String wordEntry = reader.nextLine();
            validWords.add(wordEntry); // add word into our dictionary
        }
        reader.close();
        // update attributes
        this.validWords = validWords;
    }


    /**
     * Checks to see whether the queried word is in the dictionary
     * @param query the word to check
     * @return true if the query word is in the dictionary.
     */
    public boolean containsWord(String query) {
        return this.validWords.contains(query);
    }

    /**
     * Checks for words that are one edit away from the queried word
     * @param query the word to check
     * @return a list of all valid words that are one edit away from the quer
     */
    public ArrayList<String> nearMisses(String query) {
        return new ArrayList<String>();
    }

    public static void main(String[] args) {
        WordValidation first = new WordValidation("words.txt");
        System.out.println(first.containsWord("Abellll"));
    }
}