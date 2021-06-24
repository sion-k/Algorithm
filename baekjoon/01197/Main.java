import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int V; static ArrayList<Edge> edge;

	static int kruskal() {
		int ret = 0;
		Collections.sort(edge);
		DisJointSet sets = new DisJointSet(V + 1);
		for (Edge e : edge) {
			int u = e.start; int v = e.end; int cost = e.weight;
			if (sets.find(u) == sets.find(v)) continue;
			sets.union(u, v);
			ret += cost;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		edge = new ArrayList<>(V);
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			edge.add(new Edge(A, B, C));
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
		if (rank[u] > rank[v]) {
			int temp = u; u = v; v = temp;
		}
		parent[u] = v;
		if (rank[u] == rank[v]) rank[v]++;
	}

	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

}
