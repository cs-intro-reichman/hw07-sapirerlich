
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.length()==0){
			return word2.length();
		}
		if (word2.length()==0){
			return word1.length();
		}
		if (word1.toLowerCase().charAt(0) == word2.toLowerCase().charAt(0)){
			return levenshtein(tail(word1),tail(word2));
		}
		
		int min =Math.min(levenshtein(tail(word1),word2),levenshtein(word1,tail(word2)));
		return 1+ Math.min(min,levenshtein(tail(word1),tail(word2)));
		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i=0; i<dictionary.length; i++){
		    
		    dictionary[i]=in.readString() ;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min =levenshtein(word.toLowerCase(),dictionary[0].toLowerCase());
		int min_index=0;
		for (int i=0;i<dictionary.length;i++){ 
			if (levenshtein(word.toLowerCase(),dictionary[i].toLowerCase())<=min){
				min =Math.min(min,levenshtein(word.toLowerCase(),dictionary[i].toLowerCase()));
				min_index = i;
			}
			
		}
		if (min>threshold){
			return word;
		}
		return dictionary[min_index];

	}
}
