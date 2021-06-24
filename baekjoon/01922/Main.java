import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Edge> adj;

	static int kruskal() {
		int ret = 0;
		Collections.sort(adj);
		DisjointSet s = new DisjointSet(N + 1);
		for (Edge e : adj) {
			int u = e.start; int v = e.end; int cost = e.weight;
			if (s.find(u) == s.find(v)) continue;
			s.union(u, v);
			ret += cost;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		adj = new ArrayList<>(M);
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == b) continue;
			adj.add(new Edge(a, b, c));
			adj.add(new Edge(b, a, c));
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
