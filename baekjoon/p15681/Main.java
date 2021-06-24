package baekjoon.p15681;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<ArrayList<Integer>> children;
	static boolean[] visit;

	static void dfs(int here) {
		visit[here] = true;
		for (int there : adj.get(here))
			if (!visit[there]) {
				children.get(here).add(there);
				dfs(there);
			}
	}

	static int[] cache;

	// here을 루트로 하는 서브트리의 속한 정점의 수 반환
	static int dp(int here) {
		if (cache[here] != 0) return cache[here];
		int sum = 1;
		for (int ch : children.get(here))
			sum += dp(ch);
		return cache[here] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		// 간선의 정보 adj 초기화
		adj = new ArrayList<>(); adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++)
			adj.add(new ArrayList<>());
		// 트리를 암시적으로 표현하는 children 초기화
		children = new ArrayList<>(); children.add(new ArrayList<>());
		for (int i = 1; i <= N; i++)
			children.add(new ArrayList<>());
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v); adj.get(v).add(u);
		}
		// 입력을 올바른 트리이고, R번 정점이 루트이므로 트리를 만든다
		visit = new boolean[N + 1];
		dfs(R);
		cache = new int[N + 1];
		for (int i = 0; i < Q; i++) {
			int U = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp(U)));
			bw.newLine();
		}
		bw.close();
	}

}