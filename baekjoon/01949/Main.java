import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] P;
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<ArrayList<Integer>> children;
	static int[][] cache;

	// 이번 정점을 우수 마을로 선정하는가 p ? 1 : 0
	// root를 루트로 하는 서브트리에서 얻을 수 있는 최대 우수마을 마을 주민 수
	static int dp(int p, int root) {
		if (cache[p][root] != -1) {return cache[p][root];}
		int sum = 0;
		if (p == 1) {
			sum = P[root];
			for (int ch : children.get(root))
				sum += dp(0, ch);
		} else {
			for (int ch : children.get(root))
				sum += Math.max(dp(0, ch), dp(1, ch));
		}
		return cache[p][root] = sum;
	}

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
		int N = Integer.parseInt(br.readLine());
		P = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			P[i] = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(N + 1);
		adj.add(new ArrayList<>());
		children = new ArrayList<>(N + 1);
		children.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			adj.add(new ArrayList<>());
			children.add(new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj.get(start).add(end);
			adj.get(end).add(start);
		}
		visit = new boolean[N + 1];
		dfs(1);
		cache = new int[2][N + 1];
		Arrays.fill(cache[0], -1); Arrays.fill(cache[1], -1);
		System.out.println(Math.max(dp(0, 1), dp(1, 1)));
	}

}
