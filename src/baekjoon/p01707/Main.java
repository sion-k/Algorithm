package baekjoon.p01707;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] visit; static boolean[] check;
	static ArrayList<ArrayList<Integer>> adj;

	static void DFS(int here, boolean color) {
		visit[here] = true; check[here] = color;
		for (int there : adj.get(here))
			if (!visit[there])
				DFS(there, !color);
	}

	static void DFSAll() {
		for (int here = 1; here <= N; here++)
			if (!visit[here])
				DFS(here, true);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			visit = new boolean[N + 1]; check = new boolean[N + 1];
			adj = new ArrayList<>(N + 1); adj.add(new ArrayList<>());
			for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}
			int E = Integer.parseInt(st.nextToken());
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				adj.get(start).add(end);
				adj.get(end).add(start);
			}
			DFSAll(); boolean bip = true;
			for (int here = 1; here <= N; here++) {
				for (int there : adj.get(here)) {
					if (check[here] = check[there]) {
						bip = false; break;
					}
				}
				if (!bip) {break;}
			}
			bw.write(bip ? "YES" : "NO"); bw.newLine();
		}
		bw.close();
	}
}
