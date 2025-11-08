/** Class that contains all tests on nearMisses methods */
public class Main {
    /** Main method for debugging */
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

        // Test: Substitution
        System.out.println("----------------------SUBSTITUTION TESTS----------------------");
        System.out.println(first.trySubstitute("caxtle")); // Output: "castle cattle"
        System.out.println(first.trySubstitute("cattle")); // Output: "battle castle tattle rattle wattle"
        System.out.println(first.trySubstitute("cat ")); // Output: catt cats cato
    
        // Test: Transposition
        System.out.println("----------------------TRANSPOSITION TESTS----------------------");
        System.out.println(first.tryTranspose("cat")); // Output: "act"
        
        // Test: Splitting
        System.out.println("----------------------SPLITTING TESTS----------------------");
        System.out.println(first.tryTranspose("cattell")); // Output: "catt ell cat tell"
        System.out.println(first.tryTranspose("")); // Output: ""
        
        // Test: nearMisses (complete)
        System.out.println(first.nearMisses("cest")); 
        // Output above: [rest, chest, pest, cost, test, celt, cent, vest, lest, best, nest, jest, fest, cast, zest, yest, west, cyst, crest]
        System.out.println(first.nearMisses("")); // Output: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]
        System.out.println(first.nearMisses(null)); // Output: null
    }
}

