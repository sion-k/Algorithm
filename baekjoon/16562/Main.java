import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Edge> A = new ArrayList<>(N + 1); A.add(new Edge(0, 0));
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			A.add(new Edge(i, Integer.parseInt(st.nextToken())));
		DisjointSet S = new DisjointSet(N + 1);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			S.union(u, v);
		}
		Collections.sort(A);
		int here = 0; boolean ok = true; int totalCost = 0;
		for (Edge e : A) {
			int there = e.num; int cost = e.cost;
			if (S.find(here) == S.find(there)) continue;
			// 친구비가 충분한 경우
			if (K >= cost) {
				totalCost += cost; K -= cost;
				S.union(here, there);
			} else {
				ok = false; break;
			}
		}
		if (ok)
			System.out.println(totalCost);
		else
			System.out.println("Oh no");
	}

}

class Edge implements Comparable<Edge>{
	int num; int cost;

	public Edge(int n, int c) {num = n; cost = c;}
	@Override
	public int compareTo(Edge o) {return cost - o.cost;}
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
