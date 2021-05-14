package baekjoon.p13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visit;
	static boolean found;

	// ������ ��� ���� 4������ ��θ� Ž���Ѵ�
	static void dfs(int here, int depth) {
		if (depth >= 4) {found = true; return;}
		visit[here] = true;
		for (int there : adj.get(here))
			if (!visit[there])
				dfs(there, depth + 1);
		visit[here] = false;
	}

	// ��� ������ ���� dfs�ϸ鼭 ���� 4�� ��θ� ã�Ҵ��� ��ȯ
	static boolean dfsAll() {
		for (int here = 0; here < N; here++) {
			if (found) return true;
			dfs(here, 0);
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Integer>>(N);
		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());
		visit = new boolean[N];
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		System.out.println(dfsAll() ? 1 : 0);
	}

}