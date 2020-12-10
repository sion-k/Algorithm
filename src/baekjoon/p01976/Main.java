package baekjoon.p01976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		DisjointSet s = new DisjointSet(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				if(st.nextToken().equals("1"))
					s.union(i, j);
		}
		boolean ok = true;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken()) - 1;
		start = s.find(start);
		for (int i = 1; i < M; i++) {
			int here = Integer.parseInt(st.nextToken()) - 1;
			if (start != s.find(here)) {
				ok = false; break;
			}
		}
		if (ok)
			System.out.println("YES");
		else
			System.out.println("NO");
	}

}
class DisjointSet {
	int[] parent; int[] rank;

	public DisjointSet(int n) {
		parent = new int[n];
		for (int i = 0; i < n; i++)
			parent[i] = i;
		rank = new int[n];
		Arrays.fill(rank, 1);
	}

	void union(int u, int v) {
		u = find(u); v = find(v);
		if (u == v) return;
		if (rank[u] > rank[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		parent[u] = v;
		if (rank[u] == rank[v]) rank[v]++;
	}

	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}
}