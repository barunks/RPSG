package imc.inputadapter;

import java.util.Scanner;

public class ConsoleInput implements InputAdapter<String> {
	
	private Scanner inputSource;
	
	public ConsoleInput (Scanner inputSource) {
		this.inputSource = inputSource;
	}
	
	public String nextInput() { 
		char nextChar = ' ';
		String line = inputSource.nextLine();

		if (line.length() > 0)
			nextChar = line.toUpperCase().charAt(0);
		
		return String.valueOf(nextChar);
	}
}