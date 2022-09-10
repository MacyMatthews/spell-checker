import java.util.*;
import java.io.*;

public class SpellChecker implements SpellCheckerInterface{

	private Set<String> dictionary = new HashSet<>();

	//constructor
	public SpellChecker(String filename){

		File dict = new File(filename);

		try{

			BufferedReader lineReader = new BufferedReader(new FileReader(filename));

			String current = lineReader.readLine();

			while(current != null){

				String noPunctuation = current.replaceAll("[^a-zA-Z]", "").toLowerCase();
				String[] eachWord = noPunctuation.split("\\s+");

				for(String x: eachWord){

					dictionary.add(x);

				}
				current = lineReader.readLine();
			}

		} catch(Exception e){

			e.printStackTrace();
		}

	}


	public List<String> getIncorrectWords(String filename){

		File file = new File(filename);
		List<String> badWords = new ArrayList<>();

		try{

			BufferedReader lineReader = new BufferedReader(new FileReader(filename));
			String current = lineReader.readLine();
			while(current != null){

				//need to check for numbers

				if(!current.equals("")){

					String [] theWords = current.split("\\s+");
					for(String x: theWords){

						String n = x.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

						if(dictionary.contains(n) == false && n.length() != 0){

							badWords.add(n);
						}
					}
				}

				current = lineReader.readLine();
			}

			} catch (Exception e){

				e.printStackTrace();
			}

			return badWords;

	}	


	public Set<String> getSuggestions(String word){

		Set<String> suggestions = new HashSet<String>();

		String maybeWord = "";

		for(int i = 0; i < word.length(); i++){

			maybeWord = word.toLowerCase().substring(0, i) + word.toLowerCase().substring(i + 1, word.length());
			if(dictionary.contains(maybeWord)){

				suggestions.add(maybeWord);
			}
		}



		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for(int i = 0; i < word.length(); i++){

			for(char x: alphabet){

				maybeWord = word.toLowerCase().substring(0, i) + x + 
				word.toLowerCase().substring(i, word.length());

				if(dictionary.contains(maybeWord)){

					suggestions.add(maybeWord);
				}

				if(i == word.length()-1){

					maybeWord = word + x;
				
				}
				if(dictionary.contains(maybeWord)){

					suggestions.add(maybeWord);
				}

			}

		}

		for(int i = 1; i < word.length(); i++){

			if(i != word.length()-1){

				maybeWord = word.toLowerCase().substring(0, i - 1) + 
				word.toLowerCase().substring(i, i + 1) + word.toLowerCase().substring(i - 1, i)
				+ word.toLowerCase().substring(i + 1, word.length());
			}

			else{

				maybeWord = word.toLowerCase().substring(0, i - 1) + word.toLowerCase().substring(i, i + 1)
				+ word.toLowerCase().substring(i - 1, i);
			}

			if(dictionary.contains(maybeWord)){

				suggestions.add(maybeWord);
			}
		}

		return suggestions;

	}

}

	



