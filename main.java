import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {

    public static void main(String[] args) {
        String input = "25 = int age;";

        // Patterns for token types
        String keywordPattern = "\\b(int|float|double|char|boolean)\\b";
        String identifierPattern = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b";
        String assignmentOperatorPattern = "=";
        String integerPattern = "\\b\\d+\\b";
        String semicolonPattern = ";";

        // Initialize a symbol table and two stacks
        Map<String, String> symbolTable = new LinkedHashMap<>(); // Keeps the order of insertion
        Stack<String> firstStack = new Stack<>(); // For Immediate classification store (like keywords, operators, and
                                                  // punctuation).
        Stack<String> secondStack = new Stack<>(); // For further classification, such as identifiers and literals.

        // Define token patterns and labels
        Pattern[] patterns = new Pattern[] {
                Pattern.compile(keywordPattern),
                Pattern.compile(identifierPattern),
                Pattern.compile(assignmentOperatorPattern),
                Pattern.compile(integerPattern),
                Pattern.compile(semicolonPattern)
        };

        String[] labels = { "Keyword", "Identifier", "Assignment Operator", "Integer", "Semicolon" };

        for (int i = 0; i < patterns.length; i++) {
            Matcher matcher = patterns[i].matcher(input);
            while (matcher.find()) {
                String token = matcher.group();

                // Check if token is already in symbol table to avoid duplicates
                if (symbolTable.containsKey(token)) {
                    continue;
                }

                // Categorize tokens based on their type
                if (labels[i].equals("Keyword")  labels[i].equals("Assignment Operator")
                         labels[i].equals("Semicolon")) {
                    firstStack.push(token); // Immediate classification
                    symbolTable.put(token, labels[i]);
                } else {
                    secondStack.push(token); // Needs further classification (identifiers, literals)
                    symbolTable.put(token, labels[i]);
                }

            }
        }

        // Output
        System.out.println("First Stack: " + firstStack);
        System.out.println("Second Stack: " + secondStack);
        System.out.println("\n");
        System.out.println("Symbol Table:");

        // // Print the symbol table
        for (Map.Entry<String, String> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + "\t-> Type: " +
                    entry.getValue());
        }
        System.out.println("\n");
    }
}