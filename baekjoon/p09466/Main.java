package baekjoon.p09466;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] adj;
	static boolean[] currentVisit; // 현재 진행되고 있는 DFS함수가 방문했는지 여부
	static boolean[] visit; // 이전의 DFS호출에서 정점을 방문했는지 여부
	
	static int cycle; // 사이클을 이루는 노드의 개수
	
	static void dfs(int here) {
		currentVisit[here] = true;
		int there = adj[here];
		// 이전의 DFS의 호출에서 방문하지 않은 인접 정점을 방문한다
		// 이전에 방문한 정점들은 사이클에 포함되거나 포함되지 않기 때문에 확인해줄 필요가 없다
		if (!visit[there]) {
			if (!currentVisit[there]) {
				dfs(there);
			} else {
				cycle++;
				// 사이클을 이루는 간선의 개수를 센다(간선의 개수가 노드의 개수)
				for (int i = there; i != here; i = adj[i]) cycle++;
			}
		}
		visit[here] = true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			adj = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) adj[i] = Integer.parseInt(st.nextToken());
			currentVisit = new boolean[N + 1];
			visit = new boolean[N + 1];
			cycle = 0;
			for (int here = 1; here <= N; here++)
				if (!visit[here]) dfs(here);
			System.out.println(N - cycle);
		}
	}

}