package baekjoon.p01693;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<ArrayList<Integer>> children;
	static int[][] cache;
	
	static final int INF = 987654321;
	
	static void dfs(int here, int parent) {
		for (int there : adj.get(here))
			if (there != parent) {
				children.get(here).add(there);
				dfs(there, here);
			}
	}
	
	// here�� �������� �ϴ� ����Ʈ���� �θ� prev������ ĥ���� ��,
	// Ʈ�� ��ü�� ��ĥ�ϴ� �ּ� ���
	static int dp(int here, int prev) {
		if (cache[here][prev] != 0) return cache[here][prev];
		int min = INF;
		// here ��带 c����� ĥ�Ѵ�
		for (int c = 1; c <= 10; c++) {
			if (c == prev) continue;
			int sum = c;
			for (int there : children.get(here)) sum += dp(there, c);			
			min = Math.min(min, sum);
		}
		return cache[here][prev] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList<>(N);
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		children = new ArrayList<>();
		for (int i = 0; i <= N; i++) children.add(new ArrayList<>());
		cache = new int[N + 1][11];
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v); adj.get(v).add(u);
		}
		dfs(1, 0);
		System.out.println(dp(1, 0));
	}
	
}