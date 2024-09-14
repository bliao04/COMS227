package lab2;

public class StringTest {
	
	public static void main(String[] Args) {
		String message;
		message = "Hello World!";
		System.out.println(message);
		int theLength = message.length();
		System.out.println(theLength);
		char theChar = message.charAt(0);
		System.out.println(theChar);
		theChar = message.charAt(1);
		System.out.println(theChar);
	}
}