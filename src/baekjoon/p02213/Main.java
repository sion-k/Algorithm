package baekjoon.p02213;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] W;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] parent;
	static ArrayList<ArrayList<Integer>> children;
	static boolean[] visit;
	static int[][] cache;
	static boolean[] choice;
	static List<Integer> ans = new ArrayList<>();

	static void dfs(int here) {
		visit[here] = true;
		for (int there : adj.get(here))
			if (!visit[there]) {
				parent[there] = here;
				children.get(here).add(there);
				dfs(there);
			}
	}

	// root의 부모를 선택했는가 p ? 1 : 0
	static int dp(int p, int root) {
		if (cache[p][root] != -1) {return cache[p][root];}
		int notPick = 0;
		for (int ch : children.get(root))
			notPick += dp(0, ch);
		int pick = 0;
		if (p == 0) {
			pick = W[root];
			for (int ch : children.get(root))
				pick += dp(1, ch);
		}
		if (pick > notPick) {choice[root] = true;}
		return cache[p][root] = Math.max(pick, notPick);
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
		parent = new int[N + 1];
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
		choice = new boolean[N + 1];
		System.out.println(dp(0, 1));
		bw.close();
	}

}