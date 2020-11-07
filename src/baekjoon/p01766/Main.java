package baekjoon.p01766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] visit;
	static ArrayList<PriorityQueue<Integer>> adj;

	static ArrayList<Integer> sorted = new ArrayList<>();
	static StringBuilder ans = new StringBuilder();

	static void DFS(int here) {
		visit[here] = true;
		while (!adj.get(here).isEmpty()) {
			int there = adj.get(here).poll();
			if (!visit[there]) {DFS(there);}
		}
		sorted.add(here);
	}

	static void topologicalSort() {
		for (int i = 1; i <= N; i++) {
			if (!visit[i]) {DFS(i);}
		}
		for (int i = 0; i < sorted.size(); i++) {
			ans.append(sorted.get(i)); ans.append(" ");
		}
		for (int i = 1; i <= N; i++) {
			if (!visit[i]) {ans.append(i); ans.append(" ");}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		visit = new boolean[N + 1];
		adj = new ArrayList<>(N);
		adj.add(new PriorityQueue<>());
		for (int i = 0; i < N; i++) {adj.add(new PriorityQueue<>());}
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int end = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			adj.get(start).offer(end);
		}
		topologicalSort();
		System.out.println(ans.toString().trim());
	}

}