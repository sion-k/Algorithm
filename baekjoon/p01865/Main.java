package baekjoon.p01865;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
}

public class Main {
	static int N;
	static ArrayList<ArrayList<Pair>> adj; // 인접 리스트 표현법

	static boolean hasCycle() {
		int[] upper = new int[N + 1];
		boolean updated = false;
		// N - 1번 완화 시도
		for (int i = 1; i <= N - 1; i++) {
			updated = false;
			for (int here = 1; here <= N; here++) {
				for (Pair edge : adj.get(here)) {
					int there = edge.num; int cost = edge.cost;
					// (here, there) 간선을 따라 완화 시도
					if (upper[there] > upper[here] + cost) {
						upper[there] = upper[here] + cost;
						updated = true;
					}
				}
			}
			// 모든 간선에 대해 완화시도했는데 완화되지 않으면 음수 사이클 없음
			if (!updated) {return false;}
		}
		// N번째의 완화 시도
		for (int here = 1; here <= N; here++) {
			for (Pair edge : adj.get(here)) {
				int there = edge.num; int cost = edge.cost;
				// 음수 사이클이 존재하는 경우
				if (upper[there] > upper[here] + cost) {return true;}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>(N + 1);
			adj.add(new ArrayList<>());
			for (int i = 0; i < N; i++) {adj.add(new ArrayList<>());}

			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adj.get(start).add(new Pair(end, weight));
				adj.get(end).add(new Pair(start, weight));
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adj.get(start).add(new Pair(end, -weight));
			}
			boolean ret = hasCycle();
			if (ret) {bw.write("YES");}
			else {bw.write("NO");}
			bw.newLine();
		}
		bw.close();
	}

}