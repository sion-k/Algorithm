import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		ArrayList<Edge> S = new ArrayList<>(M);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			S.add(new Edge(u, v, w));
			S.add(new Edge(v, u, w));
		}
		Collections.sort(S);
		DisjointSet set = new DisjointSet(N);
		int ret = 0;
		for (Edge e : S) {
			int u = e.start; int v = e.end; int w = e.weight;
			set.union(u, v);
			if (set.find(A) == set.find(B)) {
				ret = w; break;
			}
		}
		System.out.println(ret);
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
		if (rank[u] > rank[v]) { int temp = u; u = v; v = temp; }
		parent[u] = v;
		if (rank[u] == rank[v]) rank[v]++;
	}

	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

}

class Edge implements Comparable<Edge> {
	int start, end, weight;
	
	public Edge(int s, int e, int w) { start = s; end = e; weight = w; }
	
	@Override
	public int compareTo(Edge o) { return o.weight - weight; }
}
