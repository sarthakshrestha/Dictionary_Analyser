package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * A kind of {@link BaseAnalyser} that counts the number of unique individual
 * character occurrences within the text.
 * 
 * 
 * @author mdixon & Sarthak Shrestha
 */
public class CharFrequencyAnalyser extends BaseAnalyser {

	/**
	 * The collection containing each found character, mapped to the occurrence
	 * count.
	 * 
	 * This is a linked hash map so the order in which the characters are added is
	 * maintained.
	 */
	private Map<Character, Integer> charCounts = new LinkedHashMap<>(); // Creating the appropriate collection instance

	// Adding missing attributes (see UML model).
	int vowelCount; 
	int singleCharCount;

	//////////////////////////////////////////////////////////////////

	@Override
	public void performAnalysis(String filename) throws IOException {

		// Clear map contents and re-init other attributes.
		charCounts.clear(); // Empties the map

		selectInputFile(filename); // select the file to be analysed
		String nextWord = readNextWord();
		//Integer count = 0;
		nextWord = nextWord.trim();
		
		// vowelCount and singleCharCount Should be empty before analysis
				
		vowelCount = 0; // Initializing the vowelCount to be zero
		
		singleCharCount = 0; // Putting
		
		while (nextWord != null) {
			
			//Extract each character from the next word, and add to the occurrence map
			
			// Iff word length is 1, then increment attribute that counts single
			// character words.
			
			if(nextWord.length() == 1) {
				singleCharCount++; // Increasing singleCharCount if the length of the string 'nextWord' is 1
			}
			
			// Using for loop 
			for (int i = 0; i < nextWord.length(); i++) {			
				
				// Return the specified index which is 'i'
				Character vc = nextWord.charAt(i);
				
				// Checking if present in the map, and increasing the occurrence count
				
				// Present in the map charCounts or increment occurrence count
				if(charCounts.containsKey(vc)) {
					charCounts.put(vc, charCounts.get(vc) + 1); // Putting the appropriate keys and values
				}
				else {
					charCounts.put(vc, 1);
				}
				
				//  Check if vowel is present, if so increment correct attribute
				nextWord.toLowerCase(); // Converting the string to lower case
				if (nextWord.charAt(i) == 'a' || nextWord.charAt(i) == 'e' || nextWord.charAt(i) == 'i' || nextWord.charAt(i) == 'o'|| nextWord.charAt(i) == 'u')	{
					vowelCount++; // increasing the int value of vowelCount by increment operator
				}
			}
			

			nextWord = readNextWord(); // Passing the result of readNextWord() to nextWord
		}
	}
	
	

	@Override
	public void generateReport(PrintStream out) {

		generateHeader(out);

		out.println("Most popular character is '" + getMostPopularChar() + "' with an occurrence count of "
				+ getMostPopularCharCount());
		out.println("Unique character count is " + getUniqueCharCount());
	}

	/**
	 * Gets the most popular character of the most recent analysis.
	 * 
	 * If multiple characters have the same number of occurrences, then the first of
	 * these recorded should be returned.
	 * 
	 * @return the most popular character of the most recent analysis, this will be
	 *         null an analysis is yet to be performed.
	 */
	public Character getMostPopularChar() {
		
		// If highest occurrence count so far, record the character.
		
		Character maxchar = null; // Initializing the Character maxchar to be null
		int max = 0; // Starting the count max to be zero
		for(Entry<Character, Integer> entry: charCounts.entrySet()) { 
			if(entry.getValue() > max) {
				maxchar = entry.getKey(); // Word is the popular word after running this function
				max = entry.getValue();
			}
		}
		
		return maxchar;
	}

	/**
	 * Gets the number of times the most popular character(s) appeared within the
	 * most recent analysis.
	 * 
	 * @return the number of times the most popular character(s) appeared, 0 if an
	 *         analysis is yet to be performed.
	 */
	public int getMostPopularCharCount() {

		// Finding the most popular character count
		int max = 0;
		for(Character g:charCounts.keySet()) { // Using keySet helps to return the set view of the keys contained in charCounts
			if(charCounts.get(g)>max)max=charCounts.get(g); // See if the Character g is matched and then get the
			// count of it
			
		}

		return max ; // Returns the count of the popular word
	}

	/**
	 * Gets the number of unique characters within the analysed text.
	 * 
	 * @return the number of unique characters analysed.
	 */
	public int getUniqueCharCount() {

		// Returns  size of the map
		return charCounts.size(); 
		
	}

	/**
	 * Gets the total number of characters which are vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are vowels
	 */
	public int getVowelCount() {

		return vowelCount; // Returns the appropriate attribute which is vowelCount
	}

	/**
	 * Gets the total number of characters which are not vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are not vowels
	 */
	public int getNonVowelCount() {

		// Calculating result and returning the nonVowelCount by subtracting vowelCount from the total char Count
		
		return getResult().getTotalChars()-getVowelCount();
		// Removing the total characters of vowelCount from the total char count
	}

	/**
	 * Gets the total number of single character words present within the analysed
	 * text.
	 * 
	 * @return the total number of single character words
	 */
	public int getSingleCharacterWordCount() {

		return singleCharCount; // Returning appropriate attribute
	}

	/**
	 * Gets the total number of multi-character words present within the analysed
	 * text.
	 * 
	 * @return the total number of multi-character words
	 */
	public int getMultiCharacterWordCount() {

		// Calculating result and returning the multiCharacterWordCount by subtracting singleCharacterWordCount from the total char Count
		
		return getResult().getWordCount()-getSingleCharacterWordCount();
		// Removing the singleCharacterWordCount from the totalWordCounts results the MultiCharacter
	}

	/**
	 * Gets the number of times the given character occurred in the most recent
	 * analysis.
	 * 
	 * @param character the character for which the occurrence count is required.
	 * @return the number of times the given character appeared, 0 if it did not
	 *         ever appear.
	 */
	public int getCountOf(Character character) {

		int count = 0;
		
		// Checking whether there is character in the charCounts map by using if conditional
		if(charCounts.containsKey(character)){
			count = charCounts.get(character);
		}
		
		// Returning the associated count value.
		return count; 
		
	}

	/**
	 * Constructor
	 */
	public CharFrequencyAnalyser() {

		super("Character Frequency Analyser", "counts the number of unique character occurrences within the text");
	}

}
