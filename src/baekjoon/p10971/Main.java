package baekjoon.p10971;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] W;
	static int start;
	static boolean[] VISIT;
	static int MIN;
	static final int INF = 9000001;

	// 정점 here에서 dfs
	static void DFS(int here) {
		if (here == start && MIN != INF) {return;}
		VISIT[here] = true;
		for (int next = 0; next < N; next++) {
			if(W[here][next] != 0 && !VISIT[next]) {
				DFS(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine()); W = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {W[i][j] = Integer.parseInt(st.nextToken());}
		}
		boolean[] picked = new boolean[N];
		bw.write(String.valueOf(BFC(picked, -1)));
		bw.close();
	}

}