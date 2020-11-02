package baekjoon.p01707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static boolean[] CHECKED;
	static ArrayList<ArrayList<Integer>> adj;

	static void DFS(int here) {

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>(V + 1);
			adj.add(new ArrayList<>());
			for (int i = 1; i <= V; i++) {adj.add(new ArrayList<>());}
			CHECKED = new boolean[V + 1];

			int E = Integer.parseInt(st.nextToken());
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				adj.get(start).add(end);
			}
		}

	}
}
