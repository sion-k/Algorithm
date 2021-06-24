package baekjoon.p20010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int root = 0;
	static ArrayList<Edge> edges;
	static ArrayList<ArrayList<Pair>> mst;

	static int kruskal() {
		int ret = 0;
		Collections.sort(edges);
		DisjointSet set = new DisjointSet(N);
		for (Edge e : edges) {
			int u = e.start; int v = e.end; int w = e.weight;
			if (set.find(u) == set.find(v)) continue;
			set.union(u, v);
			mst.get(u).add(new Pair(v, w));
			mst.get(v).add(new Pair(u, w));
			root = u;
			ret += w;
		}
		return ret;
	}

	static boolean[] visit;

	// here에서 가장 멀리 있는 정점과 그곳 까지의 거리 반환
	static Pair dfs(int here) {
		visit[here] = true;
		Pair max = new Pair(here, 0);
		for (Pair p : mst.get(here)) {
			int there = p.num;
			if (!visit[there]) {
				Pair cand = dfs(there); cand.weight += p.weight;
				if (cand.weight > max.weight) {max = cand;}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>(K);
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new Edge(u, v, c));
			edges.add(new Edge(v, u, c));
		}
		// 최소 스패닝 트리를 초기화
		mst = new ArrayList<>();
		for (int i = 0; i < N; i++)
			mst.add(new ArrayList<>());
		// 최소 스패닝 트리의 가중치 합 반환
		System.out.println(kruskal());
		visit = new boolean[N];
		int leaf = dfs(root).num;
		visit = new boolean[N];
		System.out.println(dfs(leaf).weight);
	}

}

class DisjointSet {
	int[] parent; int[] rank;

	public DisjointSet(int n) {
		parent = new int[n]; for (int i = 0; i < n; i++) parent[i] = i;
		rank = new int[n];
	}

	void union(int u, int v) {
		u = find(u); v = find(v);
		if (u == v) return;
		if (rank[u] > rank[v]) {int temp = u; u = v; v = temp;}
		parent[u] = v;
		if (rank[u] == rank[v]) rank[v]++;
	}

	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

}

class Edge implements Comparable<Edge> {
	int start; int end; int weight;

	public Edge(int s, int e, int w) {start = s; end = e; weight = w;}

	@Override
	public int compareTo(Edge o) {return weight - o.weight;}

}

class Pair {
	int num; int weight;
	public Pair(int n, int w) {num = n; weight = w;}
}