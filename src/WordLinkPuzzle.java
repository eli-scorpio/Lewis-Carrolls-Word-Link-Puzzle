/* SELF ASSESSMENT 


1. readDictionary
- I have the correct method definition [Mark out of 5: 5]
- Comment: I referenced the assignment
- My method reads the words from the "words.txt" file. [Mark out of 5: 5]
- Comment: used the FileReader and BufferReader libraries and I debugged to check
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5: 5]
- Comment: Yes I used an arrayList

2. readWordList
- I have the correct method definition [Mark out of 5: 5]
- Comment: I referenced the assignment
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5: 5]
- Comment: Yes I used the debugger to check if this was the case

3. isUniqueList
- I have the correct method definition [Mark out of 5: 5]
- Comment: I referenced the assignment
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5: 5]
- Comment: Yes this is achieved with 2 for loops
- Exits the loop when a non-unique word is found. [Mark out of 5: 5]
- Comment: yes, this is done by immediately returning false
- Returns true if all the words are unique and false otherwise. [Mark out of 5: 5]
- Comment: yes

4. isEnglishWord
- I have the correct method definition [Mark out of 5: 5]
- Comment: I referenced the assignment
- My method uses the binarySearch method in Arrays library class. [Mark out of 3: 0/3]
- Comment: I used the Collections Library as Arrays did not have a function for arrayLists
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2: 2]
- Comment: Yes the Collections class' binarySearch method did this

5. isDifferentByOne
- I have the correct method definition [Mark out of 5: 5]
- Comment: I referenced the assignment
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10: 10]
- Comment: yes, it does so using a counter for the no. of times the chars are different if it is 1 then return true otherwise return false

6. isWordChain
- I have the correct method definition [Mark out of 5: 5]
- Comment: I referenced the assignment
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10: 10]
- Comment: yes, my method does all the above

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of the Java.IO classes covered in lectures [Mark out of 10: 10]
- Comment: Yes
- Asks the user for input and calls isWordChain [Mark out of 5: 5]
- Comment: Yes

 Total Mark out of 100 (Add all the previous marks): 97 || 100
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WordLinkPuzzle {

	private ArrayList<String> dictionary = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception{
		WordLinkPuzzle game = new WordLinkPuzzle();
		
		game.setDictionary(game.readDictionary());
		
		Scanner inputScanner = new Scanner(System.in);
		String stringList = "";
		boolean exit = false;
		
		System.out.println("Welcome to Word Link Puzzle Solution Checker!");
		
		while(!exit) {
			System.out.println("\nEnter a comma separated list of words (or an empty list to quit):\n");
			stringList = inputScanner.nextLine();
			
			if(!stringList.contentEquals("")) {
				ArrayList<String> list = new ArrayList<String>();
				list = game.readWordList(stringList);
				game.isWordChain(list);
			}
			else {
				exit = true;
				inputScanner.close();
				System.out.println("Goodbye!");
			}
		}
	}
	
	public ArrayList<String> readDictionary() throws Exception{
		ArrayList<String> dictionary = new ArrayList<String>();
		FileReader file = new FileReader("C:\\Users\\esker\\eclipse-workspace\\WordLinkPuzzle\\words.txt");
		BufferedReader reader = new BufferedReader(file);
		String word = reader.readLine();
		
		while(word != null) {
			dictionary.add(word);
			word = reader.readLine();
		}
		
		reader.close();
		
		return dictionary;
	}
	
	public ArrayList<String> readWordList(String stringList) {
		Scanner inputScanner = new Scanner(stringList).useDelimiter(",");
		ArrayList<String> list = new ArrayList<String>();
		while(inputScanner.hasNext()) {
			list.add(inputScanner.next().trim());
		}
		inputScanner.close();
		return list;
	}
	
	public boolean isUniqueList(ArrayList<String> list) {
		String currentWord = "";
		String referenceWord = "";
		
		for(int i = 0; i < list.size(); i++) {
			currentWord = list.get(i);
			
			for(int j = i + 1; j < list.size(); j++) {
				referenceWord = list.get(j);
				if(currentWord.equalsIgnoreCase(referenceWord))
					return false;
			}
		}
		
		return true;
	}
	
	public boolean isEnglishWord(String word) throws Exception {
		int binaryDigit = Collections.binarySearch(getDictionary(), word);
		return (((Integer) binaryDigit) >= 0)? true: false;
	}
	
	public boolean isDifferentByOne(String word, String word2) {
		int numberOfDifferentChars = 0;
		
		if(word.length() == word2.length()) {
			for(int i = 0; i < word.length(); i++) {
				char letter = word.charAt(i);
				char letter2 = word2.charAt(i);
				numberOfDifferentChars += (letter != letter2)? 1: 0;
			}
		}
		
		return (numberOfDifferentChars == 1)? true: false;
	}
	
	public void isWordChain(ArrayList<String> list) throws Exception {
		boolean noError = (list.size() >= 2)? true: false;
		
		if(isUniqueList(list) && noError) {
			for(int i = 0; i < list.size() && noError; i++) {
				int nextIndex = i + 1;
				String word = list.get(i);
				
				if(isEnglishWord(word)) {
					String word2 = (nextIndex >= list.size())? "": list.get(nextIndex);
					if(!word2.equals("")) {
						noError = isDifferentByOne(word, word2);
					}
				}
				else
					noError = false;
			}
		}
		System.out.println("\n" + (noError? "Valid": "Not a valid") + " chain of words from Lewis Carroll's word-links game.");
	}
	
	public ArrayList<String> getDictionary(){
		return dictionary;
	}
	
	public void setDictionary(ArrayList<String> dictionary) {
		this.dictionary = dictionary;
	}
}
