package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class checkPoint1 {
	
	public static void findNumWords(String filename) throws FileNotFoundException {
	    File file = new File(filename);
	    Scanner scanner = new Scanner(file);
	    while (scanner.hasNextLine()){
		    String line = scanner.nextLine();
		    int wordCount = 1;
	    	for (int i = 0; i < line.length(); i++) {
	    		if (line.charAt(i) == ' ') {
	    			wordCount += 1;
	    		} 
	    	}
	    	if (line.equals("")) {
	    		System.out.println(line);
	    	} else {
	    		System.out.println("num words: " + wordCount);
	    	}
	    }
	    scanner.close();
	  }
	
	public static void main(String[] args) throws FileNotFoundException {
		findNumWords("story.txt");
	}
	
}