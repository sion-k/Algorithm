package baekjoon.p01316;

import java.util.Scanner;

public class Main {
	
	static boolean groupWord(String word) {
		boolean[] ch = new boolean[26];
		int index = 0;
		while(index < word.length()) {
			if(ch[word.charAt(index) - 'a']) {return false;}
			ch[word.charAt(index) - 'a'] = true;
			int temp = index;
			while(index < word.length() && word.charAt(temp) == word.charAt(index)) {
				index++;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int groupWord = 0;
		for (int i = 0; i < N; i++) {
			if(groupWord(sc.nextLine())) {groupWord++;}
		}
		sc.close();
		System.out.println(groupWord);
	}

}