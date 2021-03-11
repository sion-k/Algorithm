package baekjoon.p02493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int index, value;
		public Pair(int i, int v) {index = i; value = v;}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Pair> S = new Stack<>();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			Pair p = new Pair(i, Integer.parseInt(st.nextToken()));
			int ans = 0;
			while (!S.isEmpty() && S.peek().value < p.value) S.pop();
			if (!S.isEmpty()) ans = S.peek().index;
			S.push(p);
			sb.append(ans).append(" ");
		}
		System.out.println(sb.toString().trim());
	}

}