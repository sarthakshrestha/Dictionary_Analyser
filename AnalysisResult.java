package analyser;
import java.util.ArrayList;
/**
 * Stores result information related to the analysis of text.
 *
 * @author mdixon & Sarthak Shrestha
 */
public class AnalysisResult {
	
	// Adding the missing attributes from the UML Model

	int totalChars = 0;
	int wordCount = 0;
	String longestWord = "";
	String shortestWord = "";
	String lastWord = "";
	int resetCount;
	String word;
	double getAveWordLength = 0.0;

	////////////////////////////////////////////////////////////

	/**
	 * Records a word, using the information given to calculate analysis results.
	 *
	 * Any whitespace is trimmed from either side of the word prior to it being
	 * recorded.
	 *
	 * //@param word the word to be recorded (null or empty words are ignored).
	 */

	public AnalysisResult() { // Creating the constructor
	}
	public void recordWord(String word){
		
		// Ensuring that the word is not null or empty
		
		if(word != null && !word.isEmpty()) { // Checking if the word is not empty so that the functions below can run
			
			// Removing any whitespace
			word = word.trim(); // Cuts the whitespace before and after the word
			
			// Storing the word in the last word attribute
			lastWord = word; // Puts the passed string 'word' into the last word 'lastWord'
			
			// Increasing the word count attribute
			wordCount += 1; // Increases the word count by 1 after every word
			
			ArrayList<String> wordRecords = new ArrayList<>();
			wordRecords.add(lastWord); // Adding the lastWord to our map
			
			// Using for loop to find the longestWord within the map
			for (int i = 0; i < wordRecords.size(); i++) { 
				
			// Checking if word is the longest so far, if so record in appropriate attribute	
				if (lastWord.length() > longestWord.length()) {
					longestWord = lastWord; 
				}
			}

			if (shortestWord.isEmpty()) { // if there is no shortest word then the last word is the shortest word
				
				// Checking if word is the shortest so far, if so record in appropriate attribute
				
				shortestWord = lastWord;
			} else {
				if (shortestWord.length() > lastWord.length()) { // if not empty then comparing the length with lastWord
					// and then assigning the string as the shortest word
					shortestWord = lastWord;
				}

			}
			// Adding length of word to the total character count attribute
			
			totalChars += lastWord.length(); // Used compound assignment operator
		}

	}
	/**
	 * @return total number of characters recorded.
	 */
	public int getTotalChars() {
		
		// Returning the total characters
		
		return totalChars; 
	}

	/**
	 * @return total number of words recorded.
	 */
	public int getWordCount() {
		
		// Returning the wordCount
		
		return wordCount; 
	}

	/**
	 * @return the number of times {@link #reset()} has been called.
	 */
	public int getResetCount() {
		
		// Returning the resetCount
		
		return resetCount; 
	}

	/**
	 * Gets the longest word recorded.
	 *
	 * note: If multiple longest recorded words contain the same number of
	 * characters, then the first one recorded is returned.
	 *
	 * @return the longest recorded word
	 */
	public String getLongestWord() {
		
		// Returning the longestWord
		
		return longestWord; 
	}

	/**
	 * Gets the shortest word recorded.
	 *
	 * note: If multiple shortest recorded words contain the same number of
	 * characters, then the first one recorded is returned.
	 *
	 * @return the shortest recorded word
	 */
	public String getShortestWord() {
		
		// Return the string shortestWord
		
		return shortestWord; 
	}

	/**
	 * Gets the most recently recorded word.
	 *
	 * @return the most recently recorded word.
	 */
	public String getLastWord() {
		
		// Returning the string lastWord
		
		return lastWord; 
	}

	/**
	 * Calculates and returns the average length of all recorded words.
	 *
	 * @return the average length of all recorded words. This will be 0.0 if no
	 *         words have been recorded.
	 */
	public double getAveWordLength() {
		
		// Instead of using this long method of using else if comparison I've used this one line
		// wordCount == 0 ? 0 : (double) totalChars / wordCount;
		// Using a conditional ternary operator for condensing the long if else statement
		
		//getAveWordLength = totalChars/wordCount;
		/*
		 * if(wordCount == 0){
		 * }
		 * else{
		 * average = (double) totalChars / wordCount;
		 * }
		 */

		// Calculating the average and returning it
		
		return wordCount == 0 ? 0 : (double) totalChars / wordCount;
	}

	/**
	 * Resets the analysis results back to the initial state, and increments the
	 * reset count as returned by {@link #getResetCount()}.
	 */
	public void reset() {
		
		// Reset appropriate attributes, and increment the reset count
		//Initializing all the values to 0 or empty according to the data type
		
		wordCount = 0;
		shortestWord = "";
		longestWord = "";
		lastWord = "";
		getAveWordLength = 0.0;
		totalChars = 0;
		resetCount++; // Increasing the reset count after every reset

		
		return;
	}

}
