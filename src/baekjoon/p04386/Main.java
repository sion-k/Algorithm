package baekjoon.p04386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Edge> edges;

	static double kruskal(int n) {
		double ret = 0;
		Collections.sort(edges);
		DisjointSet s = new DisjointSet(n);
		for (Edge e : edges) {
			int u = e.start; int v = e.end; double cost = e.weight;
			if (s.find(u) == s.find(v)) continue;
			s.union(u, v);
			ret += cost;
		}
		return ret;
	}

	static double dist(double[] p1, double[] p2) {
		return Math.sqrt(Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		double[][] star = new double[V][2];
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}
		edges = new ArrayList<>(V * V);
		for (int i = 0; i < V; i++)
			for (int j = i + 1; j < V; j++)
				edges.add(new Edge(i, j, dist(star[i], star[j])));
		System.out.println(kruskal(V));
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
	int start; int end; double weight;

	public Edge(int s, int e, double w) {start = s; end = e; weight = w;}
	@Override
	public int compareTo(Edge o) {return Double.compare(weight, o.weight);}
}