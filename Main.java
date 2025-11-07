public class Main {
    public static void main(String[] args) {
        // 0. Setup Word Validation
        WordValidation first = new WordValidation("words.txt");

        // Test: Deletion
        System.out.println("----------------------DELETION TESTS----------------------");
        System.out.println(first.tryDelete("buttton")); // Output: "button"
        System.out.println(first.tryDelete(" button")); // Output: "button"
        System.out.println(first.tryDelete("button ")); // Output: "button"

        // Test: Insertion
        System.out.println("----------------------INSERTION TESTS----------------------");
        System.out.println(first.tryInsert("butto")); // Output: "button"

    
    }
}

