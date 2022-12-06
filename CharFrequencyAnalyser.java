package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	private Map<Character, Integer> charCounts = new HashMap<Character, Integer>(); // TODO:Part4 create the appropriate collection instance

	// TODO:Part4 add missing attributes (see UML model).
	int vowelCount;
	int singleCharCount;

	//////////////////////////////////////////////////////////////////

	@Override
	public void performAnalysis(String filename) throws IOException {

		// TODO:Part4 clear map contents and re-init other attributes.
		charCounts.clear();

		selectInputFile(filename); // select the file to be analysed
		String nextWord = readNextWord();
		// process all available words
		while (nextWord != null) {

			// extract each character from the next word, and add to the occurrence map
			for (int i = 0; i < nextWord.length(); i++) {
				char[] strArray = nextWord.toCharArray();
				char ch = nextWord.charAt(i); // TODO:Part4 get char at position 'i' from the next word
				for(char c: strArray){
					if(charCounts.containsKey(c)){
						charCounts.put(c, charCounts.get(c) + 1);
					}
					else{
						charCounts.put(c, 1);
					}

					char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
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

		// find the most popular character
		Character character = null;
		int max = 0;
		for(Entry<Character, Integer> entry: charCounts.entrySet()){
			if(entry.getValue() > max){
				character = entry.getKey();
				max = entry.getValue();
			}
			else if(entry.getValue() == max){
				character = entry.getKey();
			}

		}

		// TODO:Part4 if highest occurrence count so far, record the character.

		return character;
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
		Character word = null;
		// TODO:Part3 find the most popular word count and return
		for(Entry<Character, Integer> entry : charCounts.entrySet()) {
			//String key = entry.getKey();
			Integer count = entry.getValue();
			if(count >= max ) {
				max = count;
			}
			else {

			}
		}

		return (max == Integer.MAX_VALUE) ? 0: max ;
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
		if(!charCounts.containsKey(vowels)){

		}
		return 0;
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

		return 0; // TODO:Part4 lookup the character in the map and return the associated count value (if any).
	}

	/**
	 * Constructor
	 */
	public CharFrequencyAnalyser() {

		super("Character Frequency Analyser", "counts the number of unique character occurrences within the text");
	}

}
