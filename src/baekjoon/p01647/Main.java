package baekjoon.p01647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int V; static int E;
	static ArrayList<Edge> edges;

	static int kruskal(ArrayList<Edge> mst) {
		int ret = 0;
		Collections.sort(edges); // 간선의 크기 순으로 정렬
		DisJointSet sets = new DisJointSet(V + 1);
		for (Edge e : edges) {
			int u = e.start; int v = e.end; int cost = e.weight;
			if (sets.find(u) == sets.find(v)) continue;
			sets.union(u, v);
			ret += cost;
			mst.add(e);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>(E);
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Edge(start, end, weight));
		}
		ArrayList<Edge> mst = new ArrayList<>(E);
		int ret = kruskal(mst);
		ret -= mst.get(mst.size() - 1).weight;
		System.out.println(ret);
	}

}

class Edge implements Comparable<Edge> {
	int start; int end; int weight;

	public Edge(int s, int e, int w) {start = s; end = e; weight = w;}

	@Override
	public int compareTo(Edge o) {return weight - o.weight;}

}
class DisJointSet {
	int[] parent; int[] rank;

	public DisJointSet(int n) {
		parent = new int[n];
		for (int i = 0; i < n; i++) parent[i] = i;
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