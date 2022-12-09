package analyser;
import java.util.ArrayList;
/**
 * Stores result information related to the analysis of text.
 *
 * @author
 */
public class AnalysisResult {

	int totalChars = 0;
	int wordCount = 0;
	String longestWord = "";
	String shortestWord = "";
	String lastWord = "";
	int resetCount;
	String word;
	double getAveWordLength = 0.0;

	// TODO::Part1 add missing attributes (use UML model to identify these)

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
		
		// TODO:Part1 ensure word is not null or empty
		
		if(word != null && !word.isEmpty()) { // Checking if the password is not empty so that the functions below can run
			
			// TODO:Part1 remove any whitespace
			word = word.trim(); // Cuts the whitespace before and after the word
			
			// TODO:Part1 store the word in the last word attribute
			lastWord = word; // Puts the passed string 'word' into the last word 'lastWord'
			
			// TODO:Part1 increment the word count attribute
			wordCount += 1; // Increases the word count by 1 after every word
			
			ArrayList<String> wordRecords = new ArrayList<>();
			wordRecords.add(lastWord); // Adding the lastWord to our map
			
			// Using for loop to find the longestWord within the map
			for (int i = 0; i < wordRecords.size(); i++) { 
				
			// TODO:Part1 check if word is the longest so far, if so record in appropriate attribute	
				if (lastWord.length() > longestWord.length()) {
					longestWord = lastWord; 
				}
			}

			if (shortestWord.isEmpty()) { // if there is no shortest word then the last word is the shortest word
				
				// TODO:Part1 check if word is the shortest so far, if so record in appropriate attribute
				
				shortestWord = lastWord;
			} else {
				if (shortestWord.length() > lastWord.length()) { // if not empty then comparing the length with lastWord
					// and then assigning the string as the shortest word
					shortestWord = lastWord;
				}

			}
			// TODO:Part1 add length of word to the total character count attribute
			
			totalChars += lastWord.length(); // Used compound assignment operator
		}

	}
	/**
	 * @return total number of characters recorded.
	 */
	public int getTotalChars() {
		// TODO:Part1 return correct attribute
		return totalChars; 
	}

	/**
	 * @return total number of words recorded.
	 */
	public int getWordCount() {
		
		// TODO:Part1 return correct attribute
		return wordCount; 
	}

	/**
	 * @return the number of times {@link #reset()} has been called.
	 */
	public int getResetCount() {
		
		// TODO:Part1 return correct attribute
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
		
		// TODO:Part1 return correct attribute
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
		
		// TODO:Part1 return correct attribute
		return shortestWord; 
	}

	/**
	 * Gets the most recently recorded word.
	 *
	 * @return the most recently recorded word.
	 */
	public String getLastWord() {
		
		// TODO:Part1 return correct attribute
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
		
		//getAveWordLength = totalChars/wordCount;
		/*
		 * if(wordCount == 0){
		 * }
		 * else{
		 * average = (double) totalChars / wordCount;
		 * }
		 */

		// TODO:Part1 calculate average and return
		return wordCount == 0 ? 0 : (double) totalChars / wordCount;
	}

	/**
	 * Resets the analysis results back to the initial state, and increments the
	 * reset count as returned by {@link #getResetCount()}.
	 */
	public void reset() {
		
		// TODO:Part1 reset appropriate attributes, and increment the reset count
		//Initializing all the values to 0 or empty
		
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
