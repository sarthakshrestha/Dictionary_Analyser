package analyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
/*
 * A kind of {@link BaseAnalyser} that identifies whether words are present
 * within a specified dictionary.
 * 
 * @author mdixon
 */
public class DictionaryAnalyser extends BaseAnalyser {
	
	//Creating HashSet of dictionary, unknown words, and known words.


	/**
	 * The set of words that represents the dictionary of known words.
	 */
	
		
	private Set<String> dictionary = new HashSet<String>();	// TODO:Part2 create the appropriate collection instance
	
	/**
	 * The set of unknown words found during the most recent analysis.
	 */
	private Set<String> unknownWords = new HashSet<String>();;	// TODO:Part2 create the appropriate collection instance

	/**
	 * The set of known words found during the most recent analysis.
	 */
	private Set<String> knownWords = new HashSet<String>();	// TODO:Part2 create the appropriate collection instance

	////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds words contained with the specified file into the dictionary of known
	 * words.
	 * 
	 * Each individual word should be on a separate line within the file, and should
	 * not contain any spaces.
	 * 
	 * Blank lines are ignored.
	 * 
	 * Any space before or after a word is trimmed.
	 * 
	 * The words are always stored as lower-case, even if they are upper-case within
	 * the file.
	 * 
	 * note: this appends to the dictionary, with any existing content remaining.
	 * 
	 * @param filename the name of the file containing the words to be added to the dictionary.
	 * @throws IOException if the named file could not be found and loaded.
	 */
	public void addToDictionary(String filename) throws IOException {
		
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String nextLine = reader.readLine();	// TODO:Part2 read the next line from the file
		while (nextLine != null) {
			
			// TODO:Part2 trim white space from the next line
			nextLine = nextLine.trim(); // Trimming the white space before and after of the String nextLine 
			
			// TODO:Part2 change the next line to be lower case
			nextLine = nextLine.toLowerCase(); // Converting the string to lower case
			
			// TODO:Part2 add the next line to the dictionary (unless it is an empty line)
			if(!nextLine.isEmpty()) { // While the string is not empty it is added to the HashSet dictionary
				dictionary.add(nextLine.toLowerCase());
			}
			
			// TODO:Part2 read the next line from the file
			nextLine = reader.readLine(); // Using BuffereReader for reading the next line					
			
			
		}

		reader.close();
	}
	
	@Override
	public void performAnalysis(String filename) throws IOException {

		// TODO:Part2 clear existing known and unknown word collections.
		knownWords.clear();
		unknownWords.clear();
		
		selectInputFile(filename);	// Selecting filename to be analyzed.

		String nextWord = readNextWord();

		// process all available words
		while (nextWord != null) {
			
			// TODO:Part2 identify whether word is within the dictionary
			if(dictionary.contains(nextWord)) {
				
				// if it is then record as a known word, otherwise record as an unknown word.
				knownWords.add(nextWord.toLowerCase());
				
			}
			else if (!dictionary.contains(nextWord)){
				unknownWords.add(nextWord.toLowerCase());
			}
						
			

			nextWord = readNextWord();
		}
	}

	@Override
	public void generateReport(PrintStream out) {
		
		generateHeader(out);
	
		out.println("The dictionary word count is " + dictionary.size());
		out.println("The number of words not present in the dictionary is " + unknownWords.size());
		out.println("The number of words present in the dictionary is " + knownWords.size());
	}

	/**
	 * Clears the current dictionary contents.
	 */
	public void clearDictionary() {

		// TODO:Part2 clear the dictionary contents
		
		dictionary.clear(); // Clearing the hashSet with the clear() function
	}
	
	/**
	 * 
	 * @return the set of words that represents the dictionary of known words.
	 */
	public Set<String> getDictionary() {

		return dictionary;	// TODO:Part2 return appropriate attribute
	}

	/**
	 * 
	 * @return the set of known words found during the most recent analysis.
	 */
	public Set<String> getKnownWords() {

		return knownWords;	// TODO:Part2 return appropriate attribute
	}

	/**
	 * 
	 * @return the set of unknown words found during the most recent analysis.
	 */
	public Set<String> getUnknownWords() {

		return unknownWords;	// TODO:Part2 return appropriate attribute
	}

	//////////////////////////////////////////////////////////////////

	/**
	 * Constructor
	 */
	public DictionaryAnalyser() {

		super("Dictionary Analyser", "checks for words which are present within a dictionary of known words");
	}
}
