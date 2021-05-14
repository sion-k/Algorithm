package baekjoon.p01991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] adj = new int[26][2];
	static StringBuilder ans = new StringBuilder();

	static void pre(int here) {
		ans.append((char)(here + 'A'));
		if (adj[here][0] != 0) {pre(adj[here][0]);}
		if (adj[here][1] != 0) {pre(adj[here][1]);}
	}

	static void in(int here) {
		if (adj[here][0] != 0) {in(adj[here][0]);}
		ans.append((char)(here + 'A'));
		if (adj[here][1] != 0) {in(adj[here][1]);}
	}

	static void post(int here) {
		if (adj[here][0] != 0) {post(adj[here][0]);}
		if (adj[here][1] != 0) {post(adj[here][1]);}
		ans.append((char)(here + 'A'));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int root = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0) - 'A';
			int right = st.nextToken().charAt(0) - 'A';
			if (left >= 0) {adj[root][0] = left;}
			if (right >= 0) {adj[root][1] = right;}
		}
		pre(0);
		System.out.println(ans);

		ans = new StringBuilder();
		in(0);
		System.out.println(ans);

		ans = new StringBuilder();
		post(0);
		System.out.println(ans);
	}

}