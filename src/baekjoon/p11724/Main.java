package baekjoon.p11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;// 정점의 수
	static boolean[][] EDGE;
	static boolean[] VISIT;
	
	// dfs(here)의 호출 횟수 반환
	static int dfsAll() {
		int cnt = 0;
		for (int here = 1; here <= N; here++) {
			if (!VISIT[here]) {
				dfs(here);
				cnt++;
			}
		}
		return cnt;
	}
	
	static void dfs(int here) {
		VISIT[here] = true;
		for (int next = 1; next <= N; next++) {
			if (EDGE[here][next] && !VISIT[next]) {
				dfs(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		EDGE = new boolean[N + 1][N + 1];
		VISIT = new boolean[N + 1];		
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			EDGE[from][to] = EDGE[to][from] = true;
		}
		br.close();
		System.out.println(dfsAll());
	}

}