import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayList<Edge> S = new ArrayList<>(N * N);
		for (int u = 1; u <= N - 1; u++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int v = u + 1; v <= N ; v++) {
				int w = Integer.parseInt(st.nextToken());
				S.add(new Edge(u, v, w));
			}
		}
		Collections.sort(S);
		ArrayList<Edge> T = new ArrayList<>(N);
		DisjointSet s = new DisjointSet(N + 1);
		for (Edge e : S) {
			int u = e.start; int v = e.end;
			if (s.find(u) == s.find(v)) continue;
			T.add(e);
			s.union(u, v);
		}
		ArrayList<PriorityQueue<Integer>> R = new ArrayList<>(N);
		for (int i = 0; i <= N; i++) R.add(new PriorityQueue<>());
		for (Edge e : T) {
			R.get(e.start).add(e.end);
			R.get(e.end).add(e.start);
		}
		for (int i = 1; i <= N; i++) {
			bw.append(R.get(i).size()).append(" ");
			while (!R.get(i).isEmpty()) bw.append(R.get(i).poll()).append(" ");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}

class DisjointSet {
	int[] parent, rank;
	
	DisjointSet(int n) { 
		parent = new int[n];
		for (int i = 0; i < n; i++) parent[i] = i;
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
	
	Edge(int u, int v, int w) { start = u; end = v; weight = w; }
	
	@Override
	public int compareTo(Edge o) { return weight - o.weight; }
	
}
