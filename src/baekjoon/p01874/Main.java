package baekjoon.p01874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static final String NEW_LINE = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> st = new Stack<Integer>();
		int x = 1;
		boolean ok = true;
		for (int i = 0; i < N; i++) {
			int y = Integer.parseInt(br.readLine());
			if (!ok) continue;
			while (y >= x) {
				st.push(x++);
				ans.append("+").append(NEW_LINE);
			}
			ans.append("-").append(NEW_LINE);
			if (st.pop() != y) ok = false;
		}
		if (ok)
			System.out.print(ans);
		else
			System.out.println("NO");
	}

}