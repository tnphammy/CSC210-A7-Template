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
        System.out.println(first.tryInsert("buton")); // Output: "button"
        System.out.println(first.tryInsert("ca")); // Output: "can cap cab car cad cat caw cam"
        System.out.println(first.tryInsert("at")); // Output: "mat pat art oat rat apt tat ant sat vat aft act ate bat cat fat eat hat"

    
    }
}

