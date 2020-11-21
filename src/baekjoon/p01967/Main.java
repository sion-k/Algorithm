package baekjoon.p01967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Pair>> adj;
	static boolean[] visit;

	static Pair DFS(Pair here) {
		visit[here.num] = true;
		int maxWeight = here.weight;
		int best = here.num;
		for (Pair there : adj.get(here.num)) {
			if (!visit[there.num]) {
				Pair cand = DFS(there);
				int thereNum = cand.num;
				int nextWeight = here.weight + cand.weight;
				System.out.println(cand);
				if (nextWeight > maxWeight) {
					maxWeight = nextWeight;
					best = thereNum;
				}
			}
		}
		return new Pair(best, maxWeight);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList<>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}
		for (int i = 0; i < N - 1; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(start).add(new Pair(end, weight));
			adj.get(end).add(new Pair(start, weight));
		}
		visit = new boolean[N + 1];
		Pair leaf = DFS(new Pair(1, 0));
		System.out.println(leaf.num);
		visit = new boolean[N + 1];
		System.out.println(DFS(leaf).weight);
	}

}

class Pair {
	int num; int weight;
	public Pair(int n, int w) {num = n; weight = w;}
	@Override
	public String toString() {
		return num + ", " + weight;
	}
}