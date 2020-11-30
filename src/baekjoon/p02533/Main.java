package baekjoon.p02533;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<ArrayList<Integer>> children;
	static int[][] cache;

	static final int INF = 1000000;

	// root�� �θ� �� ������ΰ� p ? 1 : 0
	// root�� ��Ʈ�� �ϴ� Ʈ���� ��� �� ����Ͱ� �Ǵµ� �ʿ��� �ּ� �󸮾���� ��
	static int dp(int p, int root) {
		if (children.get(root).isEmpty()) {return p == 0 ? 1 : 0;}
		if (cache[p][root] != -1) {return cache[p][root];}
		// root�� �� ����ͷ� �����ϴ� ���
		int pick = 1;
		for (int ch : children.get(root)) {pick += dp(1, ch);}
		// root�� �θ� �� ������� ��� root�� �� ����ͷ� �������� ���� �� �ִ�
		int notPick = INF;
		if (p == 1) {
			notPick = 0;
			for (int ch : children.get(root)) {notPick += dp(0, ch);}
		}
		return cache[p][root] = Math.min(pick, notPick);
	}

	static boolean[] visit;

	static void dfs(int here) {
		visit[here] = true;
		for (int there : adj.get(here)) {
			if (!visit[there]) {
				children.get(here).add(there);
				dfs(there);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList<>(N + 1);
		children = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>()); children.add(new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj.get(start).add(end);
			adj.get(end).add(start);
		}
		visit = new boolean[N + 1];
		dfs(1);
		cache = new int[2][N + 1];
		Arrays.fill(cache[0], -1); Arrays.fill(cache[1], -1);
		bw.write(String.valueOf(dp(1, 1)));
		bw.close();
	}

}