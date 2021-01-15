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

	// here에서 시작하는 경로 길이 반환 (4를 넘을 경우 4반환)
	static int dfs(int here) {
		if (found) return 4;
		visit[here] = true;
		int max = 0;
		for (int there : adj.get(here))
			if (!visit[there]) {
				max = Math.max(max, 1 + dfs(there));
				if (max >= 4) {
					found = true;
					break;
				}
			}
		visit[here] = false;
		return max;
	}

	static boolean dfsAll() {
		for (int here = 0; here < N; here++)
			if (dfs(here) >= 4)
				return true;;
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