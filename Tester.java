import java.util.*;

public class Tester{

	public static void main(String[] args){

		SpellChecker checker = new SpellChecker("words.txt");

		List<String> incorrectWords = checker.getIncorrectWords("test.txt");
		System.out.println(incorrectWords);

		for(String theWords: incorrectWords){

			Set<String> suggestions = checker.getSuggestions(theWords);
			System.out.println("Instead of: " + theWords);
			System.out.println("Try: ");
			System.out.println(suggestions);
		}

	}


}