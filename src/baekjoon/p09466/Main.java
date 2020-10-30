package baekjoon.p09466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] adj;
	static boolean[] VISIT;

	// start에서 시작한 탐색이 사이클이라면 정점 개수 반환
	static int DFS(int start, int here) {
		VISIT[here] = true;
		if (here == start) {return 1;}
		int there = adj[here];
		if (!VISIT[there]) {
			int cnt = DFS(start, there);
			if (cnt != -1) {return 1 + cnt;}
		}
		// 시작 지점이 아닌 이미 방문한 곳(사이클)에 가면 빠져나올 수 없으므로 -1
		return -1;
	}

	static void clearVisit(int here) {
		VISIT[here] = false;
		int there = adj[here];
		if (VISIT[there]) {clearVisit(there);}
	}

	static int DFSAll() {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			if(!VISIT[i]) {
				int cycle = DFS(i, adj[i]);
				if (cycle == -1) {clearVisit(i);}
				else {sum += cycle;}
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			N = Integer.parseInt(br.readLine());
			adj = new int[N + 1]; VISIT = new boolean[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				adj[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(N - DFSAll());
		}
	}

}