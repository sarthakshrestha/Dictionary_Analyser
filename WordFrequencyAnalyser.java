package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
/**
 * A kind of {@link BaseAnalyser} that counts the number of unique word
 * occurrences within the text.
 * 
 * @author mdixon & Sarthak Shrestha
 */
public class WordFrequencyAnalyser extends BaseAnalyser  {
	

	/**
	 * The collection containing each found word, mapped to the occurrence count.
	 * 
	 * This is a linked hash map so the order in which the words are added is maintained.
	 */
	private HashMap<String, Integer> wordCounts = new LinkedHashMap<String, Integer>();	// TODO:Part3 create the appropriate collection instance

	//////////////////////////////////////////////////////////////////

	@Override
	public void performAnalysis(String filename) throws IOException {

		// Clearing the word count
		wordCounts.clear();
		
		selectInputFile(filename);	// Selecting the file to be analyzed
		
		String nextWord = readNextWord();
		nextWord = nextWord.trim();
		
		// Checking if word known, if so increment the occurrence count, otherwise add with a count of 1
		// process all available words
		
		while (nextWord != null) { // Run the program within as we do not want the nextWord to be null
			if (wordCounts.containsKey(nextWord)) {
				
			 wordCounts.replace(nextWord, wordCounts.get(nextWord) + 1);
			 
			}
			else {
			 wordCounts.put(nextWord, 1);
			}
			
			nextWord = readNextWord(); ;// Reading the next word
		}
	}
	

	@Override
	public void generateReport(PrintStream out) {
		
		generateHeader(out);
	
		out.println("Most popular word is '" + getMostPopularWord() + "' with an occurrence count of "  +getMostPopularWordCount());
		out.println("Least popular word is '" + getLeastPopularWord() + "'  with an occurrence count of "  +getLeastPopularWordCount());
		out.println("Unique word count is " + getUniqueWordCount());
	}
	
	/**
	 * Gets the most popular word of the most recent analysis.
	 * 
	 * If multiple words have the same number of occurrences, then the first of these recorded should be returned.
	 * 
	 * @return the most popular word of the most recent analysis, this will be an
	 *         empty string if an analysis is yet to be performed.
	 */
	public String getMostPopularWord() {
		
		String word = "";
		int max = 0;
		
		// iterate over each entry within the map
		for (Entry<String, Integer> entry : wordCounts.entrySet()) {
			
			// If entry value (count) is higher than max, then record the key (word) as most popular word so far
		
			if(entry.getValue() > max) {
				word = entry.getKey(); // Word becomes the most popular word so far
				max = entry.getValue(); 
			}
			
		}
		
		return word;
	}

	/**
	 * Gets the number of times the most popular word(s) appeared within the most recent analysis.
	 * 
	 * @return the number of times the most popular word(s) appeared, 0 if an analysis is yet to be performed.
	 */
	public int getMostPopularWordCount() {

		int max = 0;
		
		// Finding the count of the most popular word and returning it
		
		for(Entry<String, Integer> entry : wordCounts.entrySet()) {
			
			Integer count = entry.getValue();
			if(count >= max) { // if the value is higher of the entrySet or matches the max integer then
				max = count; // Then the count is returned
			}
		}

		return (max == Integer.MAX_VALUE) ? 0: max ; // Wrapping up the if else statement with
	}
	
	/**
	 * Gets the least popular word of the most recent analysis.
	 * 
	 * If multiple words have the same least number of occurrences, then the first of these recorded should be returned.
	 * 
	 * @return the least popular word of the most recent analysis, this will be an
	 *         empty string if an analysis is yet to be performed.
	 */
	public String getLeastPopularWord() {
		
		// Finding the least popular word and returning the string 

		int min = Integer.MAX_VALUE;
		String word = "";
		
		
		for(Entry<String, Integer> entry: wordCounts.entrySet()) {
			if(entry.getValue() < min) {
				word = entry.getKey();
				min = entry.getValue();
			}
		}
		
		return word; // Returning the string that is the least popular word
	}
	
	/**
	 * Gets the number of times the least popular word(s) appeared within the most recent analysis
	 * 
	 * @return the number of times the most least word(s) appeared, 0 if an analysis is yet to be performed.
	 */
	public int getLeastPopularWordCount() {

		// Finding the least popular word
		int min = Integer.MAX_VALUE;
		
		for(Entry<String, Integer> entry : wordCounts.entrySet()) {
			Integer count = entry.getValue();
			if(count < min) {
				min = count;
			}
		}
			
		return (min == Integer.MAX_VALUE)? 0 : min;
	}
	
	/**
	 * Gets the number of unique words within the analysed text.
	 * 
	 * @return the number of unique words analysed.
	 */
	public int getUniqueWordCount() {
		
		
		return wordCounts.size();	// Returning number of entries within the word count map
	}
	
	/**
	 * Gets the number of times the given word occurred in the most recent analysis.
	 * 
	 * @param word the word for which the occurrence count is required.
	 * @return the number of times the given word appeared, 0 if it did not ever appear.
	 */
	public int getCountOf(String word) {
		
		// Looking up the word within the word count map, and return its value (count) if it exists
		
		int count = 0;
		
		for(int i = 0; i < 15; ++i) {
			if(wordCounts.containsKey(word)){
				count = wordCounts.get(word);
			}
		}
		return count;
	}
	

	/**
	 * Constructor
	 */
	public WordFrequencyAnalyser() {

		super("Word Frequency Analyser", "counts the number of unique word occurrences within the text");
	}

}
