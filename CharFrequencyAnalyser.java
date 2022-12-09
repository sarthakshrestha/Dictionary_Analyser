package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A kind of {@link BaseAnalyser} that counts the number of unique individual
 * character occurrences within the text.
 * 
 * @author mdixon
 */
public class CharFrequencyAnalyser extends BaseAnalyser {

	/**
	 * The collection containing each found character, mapped to the occurrence
	 * count.
	 * 
	 * This is a linked hash map so the order in which the characters are added is
	 * maintained.
	 */
	private Map<Character, Integer> charCounts = new LinkedHashMap<>(); // TODO:Part4 create the appropriate collection instance

	// TODO:Part4 add missing attributes (see UML model).
	int vowelCount = 0;
	int singleCharCount = 0;

	//////////////////////////////////////////////////////////////////

	@Override
	public void performAnalysis(String filename) throws IOException {

		// TODO:Part4 clear map contents and re-init other attributes.
		charCounts.clear();

		selectInputFile(filename); // select the file to be analysed
		String nextWord = readNextWord();
		//Integer count = 0;
		nextWord = nextWord.trim();
		vowelCount = 0;
		
		singleCharCount = 0;
		
		while (nextWord != null) {
			//Extract each character from the next word, and add to the occurrence map
			
			if(nextWord.length() == 1) {
				singleCharCount++;
			}
			
			
			for (int i = 0; i < nextWord.length(); i++) {			
				//nextWord = nextWord.toLowerCase();
				
				Character vc = nextWord.charAt(i);
				
				
				// Present in map or increment occurence count
				if(charCounts.containsKey(vc)) {
					charCounts.put(vc, charCounts.get(vc) + 1);
				}
				else {
					charCounts.put(vc, 1);
				}
				
				nextWord.toLowerCase();
				if (nextWord.charAt(i) == 'a' || nextWord.charAt(i) == 'e' || nextWord.charAt(i) == 'i' || nextWord.charAt(i) == 'o'|| nextWord.charAt(i) == 'u')	{
					vowelCount++;
				}
				
				// TODO:Part4 Check if present in the map, and increment occurrence count

				// TODO:Part4 check if vowel, if so increment correct attribute
			}
			// TODO:Part4 if word length is 1, then increment attribute that counts single
			// character words.

			nextWord = readNextWord();
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
		
		Character maxchar = null;
		int max = 0;
		for(Entry<Character, Integer> entry: charCounts.entrySet()) {
			if(entry.getValue() > max) {
				maxchar = entry.getKey(); // Word is the popuar word after running this function
				max = entry.getValue();
			}
		}
		
		// TODO:Part4 if highest occurrence count so far, record the character.

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

		// TODO:Part4 find the most popular character count
		int max = 0;
		for(Character g:charCounts.keySet()) {
			if(charCounts.get(g)>max)max=charCounts.get(g);
			
		}

		return max ;
	}

	/**
	 * Gets the number of unique characters within the analysed text.
	 * 
	 * @return the number of unique characters analysed.
	 */
	public int getUniqueCharCount() {

		return charCounts.size(); // TODO:Part4 return size of the map
	}

	/**
	 * Gets the total number of characters which are vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are vowels
	 */
	public int getVowelCount() {

		return vowelCount; // TODO:Part4 return appropriate attribute
	}

	/**
	 * Gets the total number of characters which are not vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are not vowels
	 */
	public int getNonVowelCount() {

		// TODO:Part4 calc result and return (hint: can use getResult().getTotalChars() to get total char count).
		
		return getResult().getTotalChars()-getVowelCount();
	}

	/**
	 * Gets the total number of single character words present within the analysed
	 * text.
	 * 
	 * @return the total number of single character words
	 */
	public int getSingleCharacterWordCount() {

		return singleCharCount; // TODO:Part4 return appropriate attribute
	}

	/**
	 * Gets the total number of multi-character words present within the analysed
	 * text.
	 * 
	 * @return the total number of multi-character words
	 */
	public int getMultiCharacterWordCount() {

		// TODO:Part4 calc result and return (hint: can use getResult().getWordCount() to get total word count).
		return getResult().getWordCount()-getSingleCharacterWordCount();
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
		
		//for(int i = 0; i < 5; ++i) {
		if(charCounts.containsKey(character)){
			count = charCounts.get(character);
		}
		//}
		
		return count; // TODO:Part4 lookup the character in the map and return the associated count value (if any).
		
	}

	/**
	 * Constructor
	 */
	public CharFrequencyAnalyser() {

		super("Character Frequency Analyser", "counts the number of unique character occurrences within the text");
	}

}
