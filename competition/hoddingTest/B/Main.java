package competition.hoddingTest.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String[] S = new String[N];
		for (int i = 0; i < N; i++) S[i] = st.nextToken();
		Arrays.sort(S);
		DisjointSet set = new DisjointSet(N);
		int K = N;
		for (int i = 0; i < N; i++) {
			
		}
		System.out.println(ans);
	}
	
}
class DisjointSet {
	int[] parent; int[] rank;
	//ArrayList<ArrayList<Integer>> children;
	
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