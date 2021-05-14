package baekjoon.p01068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] adj;
	
	// here을 루트로 하는 서브트리에서 루트의 부모가 parent일 때, 리프 노드의 개수 반환
	static int dfs(int here, int parent) {
		int sum = 0;
		for (int there = 0; there < N; there++)
			if (adj[here][there] && there != parent)
				sum += dfs(there, here);
		return sum == 0 ? 1 : sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new boolean[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int root = 0; 
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent == -1) root = i;
			else adj[i][parent] = adj[parent][i] = true;
		}
		int removed = Integer.parseInt(br.readLine());
		for (int there = 0; there < N; there++)
			adj[removed][there] = adj[there][removed] = false;
		int ret = 0;
		if (removed != root) ret = dfs(root, root);
		System.out.println(ret);
	}

}