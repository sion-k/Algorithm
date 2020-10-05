package baekjoon.p09012;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	static Stack<Character> stack;
	
	static boolean isVPS(String str) {
		stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char input = str.charAt(i);
			if (input == '(') {stack.push(input);} 
			else {
				if(!stack.isEmpty()) {stack.pop();}
				else {return false;}
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for (int c = 0; c < T; c++) {
			System.out.println(isVPS(sc.nextLine()) ? "YES" : "NO");
		}
		sc.close();
	}

}
