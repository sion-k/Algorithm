package baekjoon.p04195;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int F = Integer.parseInt(br.readLine());
			// 이름에 대해서 1부터 시작하는 번호를 매긴다
			Map<String, Integer> map = new HashMap<>();
			DisjointSet set = new DisjointSet(200001);
			int num = 1;
			for (int f = 0; f < F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String a = st.nextToken(); String b = st.nextToken();
				int u = 0; int v = 0;
				if ((u = map.getOrDefault(a, 0)) == 0) {
					u = num; // map에 존재하지 않는 사용자면 새로운 번호 부여
					map.put(a, num);
					num++;
				}
				if ((v = map.getOrDefault(b, 0)) == 0) {
					v = num; // map에 존재하지 않는 사용자면 새로운 번호 부여
					map.put(b, num);
					num++;
				}
				set.union(u, v);
				bw.write(String.valueOf(set.size[set.find(u)]));
				bw.newLine();
			}
		}
		bw.close();
	}

}

class DisjointSet {
	int[] parent; int[] rank; int[] size;

	public DisjointSet(int n) {
		parent = new int[n]; for (int i = 0; i < n; i++) parent[i] = i;
		size = new int[n]; Arrays.fill(size, 1);
		rank = new int[n];
	}

	void union(int u, int v) {
		u = find(u); v = find(v);
		if (u == v) return;
		if (rank[u] > rank[v]) {int temp = u; u = v; v = temp;}
		parent[u] = v; size[v] += size[u];
		if (rank[u] == rank[v]) rank[v]++;
	}

	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

}