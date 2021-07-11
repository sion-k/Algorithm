import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < S.length; i++) {
			if ('0' <= S[i] && S[i] <= '9' && 
			((i + 1 < S.length && (('0' <= S[i + 1] && S[i + 1] <= '9') || S[i + 1] == ')')) || i == S.length - 1)) {
				S[i] = '1';
			}
			if (S[i] == ')') {
				int sum = 0;
				while (st.peek() != -1) { sum += st.pop(); }
				st.pop();
				st.push(st.pop() * sum);
			} else {
				st.push(S[i] == '(' ? -1 : S[i] - '0');
			}
		}
		int sum = 0;
		for (int x : st) sum += x;
		System.out.println(sum);
	}

}