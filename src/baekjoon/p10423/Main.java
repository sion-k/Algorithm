package baekjoon.p10423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Edge> edges;

	static int kruskal() {
		int ret = 0;
		Collections.sort(edges);
		DisjointSet s = new DisjointSet(N + 1);
		for (Edge e : edges) {
			if (s.find(e.start) == s.find(e.end)) continue;
			s.union(e.start, e.end);
			ret += e.weight;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>(M);
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		// 0번 간선에서 발전소들로 이어지는 가중치 0짜리 간선이 있다고 가정
		for (int i = 0; i < K; i++)
			edges.add(new Edge(0, Integer.parseInt(st.nextToken()), 0));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Edge(start, end, weight));
		}
		System.out.println(kruskal());
	}

}

class Edge implements Comparable<Edge> {
	int start; int end; int weight;
	public Edge(int s, int e, int w) {start = s; end = e; weight = w;}
	@Override
	public int compareTo(Edge o) {return weight - o.weight;}
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