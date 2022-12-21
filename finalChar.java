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
	private Map<Character, Integer> charCounts=new LinkedHashMap<>(); //create the appropriate collection instance

	//  add missing attributes (see UML model).

	//////////////////////////////////////////////////////////////////
	private int vowelCount;
	private int singleCharCount;

	@Override
	public void performAnalysis(String filename) throws IOException {

		//clear map contents and re-init other attributes.
		charCounts.clear();
		singleCharCount=0;
		vowelCount=0;

		selectInputFile(filename); // select the file to be analysed

		String nextWord = readNextWord();
 
		// process all available words
		while (nextWord != null) {

			// extract each character from the next word, and add to the occurrence map
			for (int i = 0; i < nextWord.length(); i++) {

				// get char at position 'i' from the next word
				Character value =nextWord.charAt(i);
				
				// Check if present in the map, and increment occurrence count
				 if(charCounts.containsKey(value)) {  
					 charCounts.put(value,charCounts.get(value)+1);
				 }else {
					 charCounts.put(value, 1);
				 }
				// check if vowel, if so increment correct attribute
				 nextWord.toLowerCase();
				 if(nextWord.charAt(i)=='a' || nextWord.charAt(i)=='e'||nextWord.charAt(i)=='i'|| nextWord.charAt(i)=='o'|| nextWord.charAt(i)=='u' )
					 vowelCount++;
			}
			// if word length is 1, then increment attribute that counts single
			// character words.
			if(nextWord.length()==1)
				singleCharCount++;
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
		Character ch=null;
		int max=0;
		for (Entry<Character, Integer> entry : charCounts.entrySet()) {

			// if entry value (count) is higher than max, then record the key
			if(entry.getValue()>max ) {
				ch=entry.getKey(); // (word) as most popular word so far
				max=entry.getValue();
			}
		}
		return ch;
	}

	/**
	 * Gets the number of times the most popular character(s) appeared within the
	 * most recent analysis.
	 * 
	 * @return the number of times the most popular character(s) appeared, 0 if an
	 *         analysis is yet to be performed.
	 */
	public int getMostPopularCharCount() {
		//find the most popular character count
		int max = 0;

		// find the most popular word count and returned
		for(Character c:charCounts.keySet()) {
			if(charCounts.get(c)>max)
				max=charCounts.get(c);
		}
		return max;
	}

	/**
	 * Gets the number of unique characters within the analysed text.
	 * 
	 * @return the number of unique characters analysed.
	 */
	public int getUniqueCharCount() {
		
		int uniq=0;
		uniq=charCounts.size();
		return  uniq;// return size of the map
	}

	/**
	 * Gets the total number of characters which are vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are vowels
	 */
	public int getVowelCount() {

		return vowelCount; //  return appropriate attribute
	}

	/**
	 * Gets the total number of characters which are not vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are not vowels
	 */
	public int getNonVowelCount() {
		
		// calculate result and return (hint: can use getResult().getTotalChars() to get total char count).
		return getResult().getTotalChars()-getVowelCount();
	}

	/**
	 * Gets the total number of single character words present within the analysed
	 * text.
	 * 
	 * @return the total number of single character words
	 */
	public int getSingleCharacterWordCount() {

		return singleCharCount; //return appropriate attribute
	}

	/**
	 * Gets the total number of multi-character words present within the analysed
	 * text.
	 * 
	 * @return the total number of multi-character words
	 */
	public int getMultiCharacterWordCount() {

		//calculate result and return (hint: can use getResult().getWordCount() to get total word count).
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
		
		//lookup the character in the map and return the associated count value (if any).
		for(Entry<Character, Integer> entry:charCounts.entrySet()) {
			if(entry.getKey()==character)
				return entry.getValue();
		}
		return 0;
		}
		
	/**
	 * Constructor
	 */
	public CharFrequencyAnalyser() {

		super("Character Frequency Analyser", "counts the number of unique character occurrences within the text");
	}

}
