package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	private HashMap<Character, Integer> charCounts = new HashMap<>(); // TODO:Part4 create the appropriate collection instance

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
		Integer count = 0;
		nextWord = nextWord.trim();
		// process all available words
		singleCharCount = 0;
		while (nextWord != null) {
			// extract each character from the next word, and add to the occurrence map
			
			if(nextWord.length() == 1) {
				singleCharCount++;
			}
			
			
			for (int i = 0; i < nextWord.length(); i++) {			
				nextWord = nextWord.toLowerCase();
				
				//char add = nextWord.charAt(i);
				charCounts.put(nextWord.charAt(i), 0); // Extracting character from nextWord and adding to occurence map
				Character vc = nextWord.charAt(i);
				if (vc == 'a' || vc == 'e' || vc == 'i' || vc == 'o'|| vc == 'u' || vc == ' ')	{
					vowelCount++;
				}
				
				if(charCounts.containsKey(nextWord.charAt(i))) {
					//count = charCounts.replace(vc, ++count);
					charCounts.put(vc, charCounts.get(vc) + 1);
				}
				else {
					charCounts.put(vc, 1);
				}
				
					
				char[] strArray = nextWord.toCharArray();
				//char ch = nextWord.charAt(i); // TODO:Part4 get char at position 'i' from the next word
				for(char c: strArray){
				
					if(charCounts.containsKey(c)){
						charCounts.put(c, charCounts.get(c) + 1);
					}
					else{
						charCounts.put(c, 1);
					}
				}
				
				//vowelCount = vowelCount++;
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

		// find the most popular character
		
		Character maxChar = null; 
		int max = 0;
		
		Set<Entry<Character, Integer>> entrySet = charCounts.entrySet();
		
		for(Entry<Character, Integer> entry : entrySet) {
			if(entry.getValue() > max){
				maxChar = entry.getKey();
				max = entry.getValue();
			}
			
		}
			

		// TODO:Part4 if highest occurrence count so far, record the character.

		return maxChar;
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
		int max = Integer.MIN_VALUE;
		for(Entry<Character, Integer> entry : charCounts.entrySet()) {
			//String key = entry.getKey();
			Integer count = entry.getValue();
			if(count == max) {
				max = count;
			}
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
		int result = 0;
		char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
		int results = getResult().getTotalChars();
		//getResult();
		for(int i = 0; i < 5; i++) {
			if(!charCounts.containsKey(vowels)){
				result++;
			}
			else {
				result++;
			}
			
		}
		
		
		
		return result;
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
		return 0;
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
