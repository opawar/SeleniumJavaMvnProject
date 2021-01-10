package framework.main;

import java.util.Arrays;

public class MainClass {

	public static void main(String[] args) {
		String s = "hello";
		char ch[] = new char[s.length()];
		for(int i = 0; i < s.length(); i++) {
			int index = Arrays.binarySearch(ch, s.charAt(i));
			if(index > -1) {
				System.out.println("character repeated " + s.charAt(i));
			}
			else {
				ch[i] = s.charAt(i);
			}
		}
	}

}
