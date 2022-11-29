package analyser;
import java.util.Scanner.*;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.Comparator;
import java.util.List;
import java.io.*;

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

	//int[] words = {Integer.parseInt(word)};

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

	public AnalysisResult() {
	}
	public void recordWord(String word){
		if(word != null && !word.isEmpty()) {
			word = word.trim();
			lastWord = word;
			wordCount += 1;
			ArrayList<String> wordRecords = new ArrayList<>();
			wordRecords.add(lastWord);

			for (int i = 0; i < wordRecords.size(); i++) {
				if (lastWord.length() > longestWord.length()) {
					longestWord = lastWord;
				}
			}

			if (shortestWord.isEmpty()) {
				shortestWord = lastWord;
			} else {
				if (shortestWord.length() > lastWord.length()) {
					shortestWord = lastWord;
				}

			}
			totalChars += lastWord.length();
		}

		//String longestWord = Stream.of(lastWord).max(Comparator.comparingInt(String::length)).get();

	}

	// TODO:Part1 ensure word is not null or empty

	// TODO:Part1 remove any whitespace

	// TODO:Part1 store the word in the last word attribute

	// TODO:Part1 increment the word count attribute

	// TODO:Part1 check if word is the longest so far, if so record in appropriate attribute

	// TODO:Part1 check if word is the shortest so far, if so record in appropriate attribute

	// TODO:Part1 add length of word to the total character count attribute

	/**
	 * @return total number of characters recorded.
	 */
	public int getTotalChars() {

		return totalChars; // TODO:Part1 return correct attribute
	}

	/**
	 * @return total number of words recorded.
	 */
	public int getWordCount() {

		return wordCount; // TODO:Part1 return correct attribute
	}

	/**
	 * @return the number of times {@link #reset()} has been called.
	 */
	public int getResetCount() {

		return resetCount; // TODO:Part1 return correct attribute
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

		return longestWord; // TODO:Part1 return correct attribute
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

		return shortestWord; // TODO:Part1 return correct attribute
	}

	/**
	 * Gets the most recently recorded word.
	 *
	 * @return the most recently recorded word.
	 */
	public String getLastWord() {

		return lastWord; // TODO:Part1 return correct attribute
	}

	/**
	 * Calculates and returns the average length of all recorded words.
	 *
	 * @return the average length of all recorded words. This will be 0.0 if no
	 *         words have been recorded.
	 */
	public double getAveWordLength() {

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
		wordCount = 0;
		shortestWord = "";
		longestWord = "";
		lastWord = "";
		getAveWordLength = 0.0;
		totalChars = 0;
		resetCount++;

		// TODO:Part1 reset appropriate attributes, and increment the reset count
		return;
	}

}
