import java.io.*;
import java.util.*;

class FindCompound {
    private TreeMap<String, Boolean> wTree;   // TreeMap used to store words by length in decreasing order
    private HashMap<String, Boolean> wMap;    // HashMap used to store words for quick lookup

    public FindCompound() {
        this.wTree = new TreeMap<>(new SortByLengthDecreasing());  // Initializing TreeMap with custom comparator
        this.wMap = new HashMap<>();   // Initializing HashMap for quick lookup
    }

    public void readWordsFromFile(String documentName) throws IOException {
        File document = new File(documentName);   // Creating a File object from the documentName
        BufferedReader br = new BufferedReader(new FileReader(document));  // Initializing a BufferedReader to read the file
        String line;
        while ((line = br.readLine()) != null) {
            String str = line.trim();   // Read and trim each line from the file
            wTree.put(str, true);   // Adding the word to the TreeMap
            wMap.put(str, true);   // Adding the word to the HashMap for quick lookup
        }
        br.close();   // Closing the BufferedReader
    }

    public String[] findLongestCompound() {
        String[] result = new String[2];   // Initializing an array to store the longest and second-longest compound words
        boolean Longest = false;   // Flag used to track if the longest compound word is found
        boolean SecondLongest = false;   // Flag used to track if the second longest compound word is found

        for (Map.Entry<String, Boolean> entry : wTree.entrySet()) {
            String str = entry.getKey();
            if (checkPossibleOutcome(str, wMap)) {  // Checking if the word can be a compound word
                if (!Longest) {
                    result[0] = str;   // Storing it as the longest compound word
                    Longest = true;
                } else if (!SecondLongest) {
                    result[1] = str;   // Storing it as the second longest compound worrd
                    SecondLongest = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean checkPossibleOutcome(String str, HashMap<String, Boolean> map) {
        if (str.length() == 0) {
            return false;   // An empty string cannot be a compound word therefore returning false
        }
        if (str.length() == 1) {
            return map.containsKey(str);  // If the word is a single character then it should exist in the dictionary
        }
        for (int i = 1; i < str.length(); i++) {
            String firstSubstring = str.substring(0, i);
            String secondSubstring = str.substring(i);
            if (map.containsKey(firstSubstring)) {
                if (map.containsKey(secondSubstring) || checkPossibleOutcome(secondSubstring, map)) {
                    return true;   // Recursively checking if both substrings are compound words
                }
            }
        }
        return false;   // If no compound word is found then returning false
    }
}

class SortByLengthDecreasing implements Comparator<String> {
    public int compare(String first, String second) {
        return second.length() - first.length();  // Comparing words by length in decreasing order
    }
}

class Impledge {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String documentName = sc.nextLine();  // Reading the document/file name from the user
        sc.close();

        FindCompound compoundWord = new FindCompound();
        compoundWord.readWordsFromFile(documentName);  // Reading words from the document/file
        long startTime = System.currentTimeMillis();
        String[] longestCompoundWords = compoundWord.findLongestCompound();  // Finding the longest and second longest compound words
        long endTime = System.currentTimeMillis();

        System.out.println("Longest Compound Word is: " + longestCompoundWords[0]);  // Printing the results
        System.out.println("Second Longest Compound Word is: " + longestCompoundWords[1]);
        System.out.println("Time required to complete the execution is: " + (endTime - startTime) + " milliseconds");//Printing the time of execution
    }
}
