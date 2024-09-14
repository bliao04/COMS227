package lab5;

public class Initials {
	
	public static String findInitials(String name) {
		boolean spaceBefore = false;
		String initials = "";
		initials += name.charAt(0);
		for (int i = 0; i < name.length(); i++) {
			if (spaceBefore == true) {
				initials += name.charAt(i);
			} 
			if (name.charAt(i) == ' ') {
				spaceBefore = true;
			} else {
				spaceBefore = false; 
			}
		}
		return initials;
	}
	
	public static int findVowel(String name) {
		boolean foundVowel = false;
		for (int i = 0; i < name.length(); i++) {
			if ("aeiouAEIOU".indexOf(name) >= 0) {
				foundVowel = true;
			} else {
				foundVowel = false;
			}
			if (foundVowel == true) {
				return i;
			} else {
				continue;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String name1 = "Edna del Humboldt von der Schooch";
		System.out.println(findInitials(name1));
		String vowel1 = "AEIOU";
		String vowel2 = "FGVBL";
		System.out.println(findVowel(vowel1));
		System.out.println(findVowel(vowel2));
	}
}
