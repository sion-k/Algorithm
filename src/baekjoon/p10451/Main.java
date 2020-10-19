package baekjoon.p10451;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] adj;
	static int start;
	static boolean[] VISIT;

	// DFS과정에서 사이클이 존재하는지 반환
	static boolean DFS(int here) {
		VISIT[here] = true;
		if (here == start) {return true;}
		if (!VISIT[adj[here]]) {return DFS(adj[here]);}
		return false;
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
				boolean[] temp = VISIT.clone();
				start = i;
				if (!DFS(adj[i])) {VISIT = temp;}
				else {cnt++;}
			}
			bw.write(String.valueOf(cnt));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}