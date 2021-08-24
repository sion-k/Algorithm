import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		char[] S = br.readLine().toCharArray();
		Stack<Character> st = new Stack<>();
		for (char x : S) {
			switch(x) {
			case '(':
				st.push(x);
				break;
			case ')':
				while (st.peek() != '(') bw.append(st.pop());
				st.pop();
				break;
			case '+' : case '-' :
				while (!st.isEmpty() && st.peek() != '(')
					bw.append(st.pop());
				st.push(x);
				break;
			case '*' : case '/' :
				while (!st.isEmpty() && (st.peek() == '*' || st.peek() == '/'))
					bw.append(st.pop());
				st.push(x);
				break;
			default:
				bw.append(x);
			}
		}
		while (!st.isEmpty()) bw.append(st.pop());
		System.out.println(bw);
	}

}
