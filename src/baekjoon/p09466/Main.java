package baekjoon.p09466;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] adj;
	static boolean[] visit;

	static int DFS(int here, int start) {
		visit[here] = true;
		int there = adj[here];
		int sum = 1;
		if (!visit[there]) {
			int cnt = DFS(there, start);
			if (cnt == 0) {sum = 0;}
			else {sum += cnt;}
		} else if (there != start) {
			sum = 0;
		}
		if (sum == 0) {visit[here] = false;}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			adj = new int[N + 1]; visit = new boolean[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				adj[i] = Integer.parseInt(st.nextToken());
			}
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				if (!visit[i]) {sum += DFS(i, i);}
			}
			bw.write(String.valueOf(N - sum)); bw.newLine();
		}
		bw.close();
	}

}