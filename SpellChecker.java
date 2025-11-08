import java.util.Scanner; // Import the Scanner class
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Class that checks the spelling of each word and offers suggestions if needed
 */
public class SpellChecker {
    /** Main method to run and debug */
    public static void main(String[] args) {
        WordValidation validator = new WordValidation("words.txt");
        Scanner reader = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter any word(s):");

        while(reader.hasNextLine()) {
            String entry = reader.nextLine(); // read the input 
            // 1. Store input as HashSet
            String[] inputs = entry.split(" ");
            HashSet<String> wordSet = new HashSet<String>();
            for (String input : inputs) {
                String inputWithoutPunct = input.replaceAll("\\p{Punct}", "");
                wordSet.add(inputWithoutPunct);
            }
            // 2. Check each word in HashSet
            for (String word : wordSet) {
                // Check if word is legal
                if (validator.containsWord(word.toLowerCase())) {
                    System.out.println("'" + word + "'" + " is spelled correctly.");
                }
                // Give suggestions if not
                else {
                    System.out.println("Not found: " + word);
                    ArrayList<String> suggestions = validator.nearMisses(word);
                    String allSuggestions = "";
                    for (String suggestion : suggestions) {
                        if (allSuggestions.equals("")) {
                            allSuggestions += suggestion;
                        } else {
                            allSuggestions += " ";
                            allSuggestions += suggestion;
                        }
                    }
                    if (allSuggestions.equals("")) {
                        System.out.println("Suggestions: Unable to find");
                    } else {
                        System.out.println("Suggestions: " + allSuggestions);
                    }
                }
            } 
            if (entry.toLowerCase().equals("please stop")) {
                reader.close();
            }
            
        }
        reader.close();
    }

}