package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFSandBFS {
	static int N; // 정점 수
	static int M; // 간선 수
	static boolean[][] EDGE;// 인접 행렬 표현법
	static boolean[] VISIT;// DFS용
	static boolean[] BOOKED;// BFS용
	static int[] DIS;// 최단 거리
	
	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	// 정점 here에서 dfs
	static void dfs(int here) {
		VISIT[here] = true;
		for (int next = 1; next <= N; next++) {
			if(EDGE[here][next] && !VISIT[next]) {
				dfs(next);
			}
		}
	}
	
	// 정점 start에서 bfs
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		BOOKED[start] = true;
		
		while(!q.isEmpty()) {
			int here = q.poll();
			for (int next = 1; next <= N; next++) {
				if(EDGE[here][next] && !BOOKED[next]) {
					q.offer(next);
					BOOKED[next] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		VISIT = new boolean[N + 1];
		BOOKED = new boolean[N + 1];
		EDGE = new boolean[N + 1][N + 1];
		
		M = Integer.parseInt(st.nextToken());
		
		int V = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int here = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			EDGE[here][next] = EDGE[next][here]  = true;
		}
		dfs(V);
		bfs(V);
	}

}