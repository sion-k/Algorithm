package baekjoon.p01918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		StringBuilder A = new StringBuilder();
		for (char ch : S) {
			switch (ch) {
			case '(' : case '*' : case '/' : stack.push(ch); break;
			case ')' :
				while (stack.peek() != '(') A.append(stack.pop());
				stack.pop(); break;
			case '+' : case '-' :
				while (!stack.isEmpty() &&
					   (stack.peek() == '*' || stack.peek() == '/'))
					A.append(stack.pop());
				stack.push(ch); break;
			default :
				A.append(ch); break;
			}
		}
		while (!stack.isEmpty())
			A.append(stack.pop());
		System.out.println(A);
	}

}