# Impledge_question

Overview
The aforementioned Java program may determine which compound words in a text document are the longest and second-longest. The code makes use of a HashMap for instantaneous word lookup and a TreeMap for arranging words according to length in decreasing order. The program utilises a recursive algorithm to locate complex terms inside the text.

Design Decisions and Approach
Data Structures:
The program uses TreeMap (wTree) for sorted words by length. HashMap (wMap) is for quick word lookup. Reading method populates both. findLongestCompound identifies longest and second-longest compounds from wTree. checkPossibleOutcome recursively validates compound words. SortByLengthDecreasing sorts words by length. Main program times execution and prints results.

Steps to Execute
To execute the FindCompound program, follow these steps:
1. Compile the Java program:
javac FindCompound.java

2.Run the program and provide the name of the document as input. For example:

java Impledge
Enter the document name: document.txt

3.The program will process the document and display the longest and second-longest compound words along with the execution time.

4.You can replace document.txt with the path to your own document/file.

Note
Ensure that the input document is in the same directory as the Java files or provide the correct path to the document when running the program.
