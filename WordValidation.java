import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

public class WordValidation implements SpellingOperations {
    public HashSet<String> validWords = null;

    /**
     * Constructor
     * 
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
        while (reader.hasNextLine()) {
            String wordEntry = reader.nextLine();
            validWords.add(wordEntry); // add word into our dictionary
        }
        reader.close();
        // update attributes
        this.validWords = validWords;
    }

    /**
     * Checks to see whether the queried word is in the dictionary
     * 
     * @param query the word to check
     * @return true if the query word is in the dictionary.
     */
    public boolean containsWord(String query) {
        return this.validWords.contains(query);
    }

    /**
     * Convert a Linked List of String elements to a String
     * 
     * @param ll the LinkedList
     * @return the String representation of ll
     */
    public String llToString(LinkedList<String> ll) {
        String res = "";
        ListIterator<String> it = ll.listIterator();
        while (it.hasNext()) {
            String curr = it.next();
            res += curr;
        }
        return res;
    }

    /**
     * Convert a String to a LinkedList of its letters
     * 
     * @param str the String
     * @return the LinkedList representation of str
     */
    public LinkedList<String> stringToLinkedList(String str) {
        LinkedList<String> res = new LinkedList<String>();
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            String letter = String.valueOf(character);
            res.add(letter);
        }
        return res;
    }

    /**
     * Convert a HashSet with String elements into a String
     * 
     * @param set the HashSet
     * @return the desired String representation
     */
    public String hashSetToString(HashSet<String> set) {
        String res = "";
        for (String each : set) {
            if (res == "") {
                res += each; // no space for first element
            } else {
                res += " ";
                res += each;
            }
        }
        return res;
    }

    /**
     * Checks for words that are one edit away from the queried word
     * 
     * @param query the word to check
     * @return a list of all valid words that are one edit away from the quer
     */
    public ArrayList<String> nearMisses(String query) {
        return new ArrayList<String>();
    }

    /**
     * Generate legal (within dictionary) words by deleting one letter of the word
     * 
     * @param word
     * @return all legal words as a String
     */
    public String tryDelete(String word) {
        // 1. Have a suggestions hashSet
        HashSet<String> finalSuggestions = new HashSet<String>();
        // 2. Perform deletion procedure on each word
        // 2.0. Setup word as a LinkedList
        LinkedList<String> wordLL = this.stringToLinkedList(word);
        // 2.1. Generate deletion of each letter

        // LETS GET DOWN TO BUSINESS
        // 1. Traverse the word to delete each letter
        ListIterator<String> bigIt = wordLL.listIterator();
        while (bigIt.hasNext()) {
            // 1. Make word clone to operate on
            LinkedList<String> clone = this.stringToLinkedList(word);
            // 2. Find deletion spot (whichever letter the bigIt is at)
            ListIterator<String> deleteIt = clone.listIterator();
            while (deleteIt.nextIndex() != bigIt.nextIndex()) {
                deleteIt.next();
            }
            // 3. Delete at spot, on clone => LinkedList result
            deleteIt.next();
            deleteIt.remove();
            // 4. Convert result into String format
            String res = this.llToString(clone);
            // 5. Check result eligibility -> add to 'suggestions' if yes
            if (this.containsWord(res)) {
                finalSuggestions.add(res);
            }
            // Advance bigIt()
            bigIt.next();
        }

        // 3. Return 'suggestions'
        return this.hashSetToString(finalSuggestions);
    }

    /**
     * Generate legal (within dictionary) words by deleting one letter of the word
     * 
     * @param word
     * @return all legal words as a String
     */
    public String tryInsert(String word) {
        // 0. Have the alphabet
        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        // 1. Have a suggestions hashSet
        HashSet<String> finalSuggestions = new HashSet<String>();
        // 2. Perform deletion procedure on each word
        // 2.0. Setup word as a LinkedList
        LinkedList<String> wordLL = this.stringToLinkedList(word);
        // 2.1. Generate insertion at each spot

        // LETS GET DOWN TO BUSINESS
        // 1. Traverse the word to find a spot
        ListIterator<String> bigIt = wordLL.listIterator();
        while (bigIt.hasNext()) {
            // 1. Make word clone to operate on
            LinkedList<String> clone = this.stringToLinkedList(word);
            // 2. Find insertion spot (whichever letter the bigIt is at)
            ListIterator<String> deleteIt = clone.listIterator();
            while (deleteIt.nextIndex() != bigIt.nextIndex()) {
                deleteIt.next();
            }
            // 3. Insert each letter of the alphabet into spot
            for (String letter : alphabet) {
                // 1. Actually insert
                deleteIt.add(letter);
                // 2. Convert result into String format
                String res = this.llToString(clone);
                // 3. Check result eligibility -> add to 'suggestions' if yes
                if (this.containsWord(res)) {
                    finalSuggestions.add(res);
                }
                // 4. Delete inserted letter for reusing
                deleteIt.previous(); // this returns the letter just inserted
                deleteIt.remove();
                System.out.println("res: " + res);
                System.out.println("clone afer removal: " + clone);
            }

            // Advance bigIt() (if able to - not executed at final spot)
            bigIt.next();
            
        }
        // Tries once for final spot
        LinkedList<String> clone = this.stringToLinkedList(word);
        ListIterator<String> finalIt = clone.listIterator();
        while (finalIt.hasNext()) {
            finalIt.next();
        }
        for (String letter : alphabet) {
            // 1. Actually insert
            finalIt.add(letter);
            // 2. Convert result into String format
            String res = this.llToString(clone);
            // 3. Check result eligibility -> add to 'suggestions' if yes
            if (this.containsWord(res)) {
                finalSuggestions.add(res);
            }
            // 4. Delete inserted letter for reusing
            finalIt.previous(); // this returns the letter just inserted
            finalIt.remove();
            System.out.println("res: " + res);
            System.out.println("clone afer removal: " + clone);
        }

        // 3. Return 'suggestions'
        return this.hashSetToString(finalSuggestions);
    }

    public static void main(String[] args) {
        WordValidation first = new WordValidation("words.txt");
        System.out.println(first.tryInsert("at"));

    }
}