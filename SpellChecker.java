import java.util.Scanner;  // Import the Scanner class
import java.util.HashSet;
import java.util.ListIterator;
import java.util.ArrayList;
/** Class that checks the spelling of each word and offers suggestions if needed */
public class SpellChecker{
    /** Main method to run and debug */
    public static void main(String[] args) {
        WordValidation validator = new WordValidation("words.txt");
        Scanner reader = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter any word(s):");

        String entry = reader.nextLine(); // read the input
        // 1. Store input as HashSet
        String[] inputs = entry.split(" ");
        HashSet<String> wordSet = new HashSet<String>();
        for (String input : inputs) {
            wordSet.add(input);
        }
        // 2. Check each word in HashSet
        for (String word : wordSet) {
            // Check if word is legal
            if (validator.containsWord(word)) {
                System.out.println("'" + word + "'" + " is spelled correctly." );
            }
            // Give suggestions if not
            else {
                System.out.println("Not found: " + word);
                ArrayList<String> suggestions = validator.nearMisses(word);
                String allSuggestions = "";
                for (String suggestion : suggestions) {
                    if(allSuggestions.equals("")) {
                        allSuggestions += suggestion;
                    }
                    else {
                        allSuggestions += " ";
                        allSuggestions += suggestion;
                    }
                }
                System.out.println("Suggestions: " + allSuggestions);
            }
        }
            
        reader.close();
    }
    
}