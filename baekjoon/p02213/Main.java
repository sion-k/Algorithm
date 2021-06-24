package baekjoon.p02213;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] W;
	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<ArrayList<Integer>> children;
	static boolean[] visit;
	static int[][] cache;
	static boolean[] choice;

	static void dfs(int here) {
		visit[here] = true;
		for (int there : adj.get(here))
			if (!visit[there]) {
				children.get(here).add(there);
				dfs(there);
			}
	}

	// root를 선택하는가 p ? 1 : 0;
	static int dp(int p, int root) {
		if (cache[p][root] != -1) {return cache[p][root];}
		if (p == 1) { // root를 선택한 경우
			int sum = W[root];
			// 자식은 선택할 수 없다
			for (int ch : children.get(root))
				sum += dp(0, ch);
			return cache[p][root] = sum;
		} else { // root를 선택하지 않은 경우
			int sum = 0;
			// 자식은 선택할 수도 선택하지 않을 수도 있다.
			for (int ch : children.get(root))
				sum += Math.max(dp(1, ch), dp(0, ch));
			return cache[p][root] = sum;
		}
	}

	// root를 선택하는가 p ? 1 : 0;
	static void reconstruct(int p, int root) {
		if (p == 1) {
			choice[root] = true;
			for (int ch : children.get(root))
				reconstruct(0, ch);
		} else {
			for (int ch : children.get(root))
				if (dp(0, ch) < dp(1, ch)) {reconstruct(1, ch);}
				else {reconstruct(0, ch);}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		W = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			W[i] = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(); adj.add(new ArrayList<>());
		children = new ArrayList<>(); children.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			adj.add(new ArrayList<>());
			children.add(new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj.get(start).add(end); adj.get(end).add(start);
		}
		visit = new boolean[N + 1];
		dfs(1);
		cache = new int[2][N + 1];
		Arrays.fill(cache[0], -1); Arrays.fill(cache[1], -1);
		int flag = dp(0, 1) > dp(1, 1) ? 0 : 1;
		bw.write(String.valueOf(dp(flag, 1)));
		bw.newLine();
		choice = new boolean[N + 1];
		reconstruct(flag, 1);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++)
			if (choice[i])
				sb.append(i).append(" ");
		bw.write(sb.toString().trim());
		bw.newLine();
		bw.close();
	}

}