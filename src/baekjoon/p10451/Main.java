package baekjoon.p10451;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] adj;
	static boolean[] VISIT;

	static void DFS(int here) {
		VISIT[here] = true;
		if (!VISIT[adj[here]]) {DFS(adj[here]);}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			adj = new int[N + 1]; VISIT = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {adj[i] = Integer.parseInt(st.nextToken());}
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (!VISIT[i]) {
					DFS(i);
					cnt++;
				}
			}
			bw.write(String.valueOf(cnt));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}