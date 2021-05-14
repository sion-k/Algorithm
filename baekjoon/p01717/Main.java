package baekjoon.p01717;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		DisjointSet s = new DisjointSet(N + 1);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int com = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (com == 0) {
				s.union(a, b);
			} else if (s.find(a) == s.find(b)){
				bw.write("YES");
				bw.newLine();
			} else {
				bw.write("NO");
				bw.newLine();
			}
		}
		bw.close();
	}

}

class DisjointSet {
	int[] parent; int[] rank;

	public DisjointSet(int n) {
		parent = new int[n]; rank = new int[n];
		for (int i = 0; i < n; i++)
			parent[i] = i;
		Arrays.fill(rank, 1);
	}

	// u���� ��ũ�� ���� v�� u�� ������ �ִ´�
	void union (int u, int v){
		u = find(u); v = find(v);
		if (u == v) return; // �̹� ���� ���տ� ���� ��� ���� ó��
		if (rank[u] > rank[v]) { // v�� rank�� �� ���� ����� �ش�
			int temp = u;
			u = v; v = temp;
		}
		parent[u] = v; // v�� �ؿ� u�� �ִ´�
		// ���� rank�� ���Ҵٸ� rank�� �����Ѵ�
		if (rank[u] == rank[v]) rank[v]++;
	}

	// u�� ���� ������ ��ȯ
	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}
}