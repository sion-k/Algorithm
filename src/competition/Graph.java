package competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph {
	static int V; // 정점의 개수
	static int N; static int M; // 2차원
	static boolean[][] adj;// 인접 행렬 표현법
	static boolean[] visit;// DFS용

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// 정점 here에서 dfs
	static void dfs(int here) {
		visit[here] = true;
		for (int next = 0; next < N; next++) {
			if(adj[here][next] && !visit[next]) {
				dfs(next);
			}
		}
	}

	// 정점 start에서 bfs
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		boolean[] BOOKED = new boolean[N];
		BOOKED[start] = true;

		while(!q.isEmpty()) {
			int here = q.poll();
			for (int next = 1; next <= N; next++) {
				if(adj[here][next] && !BOOKED[next]) {
					q.offer(next);
					BOOKED[next] = true;
				}
			}
		}
	}

	// 정점 from에서 to까지 최단 거리 반환. 도달 불가능하면 -1 반환
	static int bfs(int from, int to) {
		Queue<Integer> q = new LinkedList<>();
		q.add(from);
		int[] dist = new int[N];
		Arrays.fill(dist, -1);
		dist[from] = 0;

		while(!q.isEmpty()) {
			int here = q.poll();
			for (int next = 1; next <= N; next++) {
				if(adj[here][next] && dist[next] != -1) {
					if (next == to) {return dist[here] + 1;}
					q.offer(next); BOOKED[next] = true;
					dist[next] = dist[here] + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		visit = new boolean[N + 1];
		BOOKED = new boolean[N + 1];
		adj = new boolean[N + 1][N + 1];

		M = Integer.parseInt(st.nextToken());

		int V = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int here = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			adj[here][next] = adj[next][here]  = true;
		}
		dfs(V);
		bfs(V);
	}

}