package baekjoon.p1260;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw;
	
	static int N;
	// 인접 행렬 표현법
	static int M;
	static boolean[][] EDGE;
	static boolean[] VISIT;
	
	// 정점 here에서 dfs
	static void dfs(int here) throws IOException {
		VISIT[here] = true;
		bw.write(String.valueOf(here));
		for (int next = 1; next <= N; next++) {
			if(!EDGE[here][next] && !VISIT[next]) {
				bw.write(" ");
				dfs(next);
			}
		}
	}
	
	// 정점 here에서 bfs
	static void bfs(int here) {
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		VISIT = new boolean[N + 1];
		EDGE = new boolean[N + 1][N + 1];
		
		M = Integer.parseInt(st.nextToken());
		
		int V = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			EDGE[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		
		dfs(V);
		bw.newLine();
		
	}

}
