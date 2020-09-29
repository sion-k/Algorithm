package baekjoon.p1260;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N;
	// 인접 행렬 표현법
	static int M;
	static boolean[][] EDGE;
	// DFS용
	static boolean[] VISIT;
	// BFS용
	static boolean[] BOOKED;
	
	// 정점 here에서 dfs
	static void dfs(int here) throws IOException {
		VISIT[here] = true;
		bw.write(String.valueOf(here));
		for (int next = 1; next <= N; next++) {
			if(EDGE[here][next] && !VISIT[next]) {
				bw.write(" ");
				dfs(next);
			}
		}
		bw.flush();
	}
	
	// 정점 start에서 bfs
	static void bfs(int start) throws IOException {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		BOOKED[start] = true;
		
		while(!q.isEmpty()) {
			int here = q.poll();
			bw.write(String.valueOf(here));
			for (int next = 1; next <= N; next++) {
				if(EDGE[here][next] && !BOOKED[next]) {
					q.offer(next);
					BOOKED[next] = true;
				}
			}
			if(!q.isEmpty()) {
				bw.write(" ");
			}
		}
		bw.flush();
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
		bw.newLine();
		bfs(V);
		bw.newLine();
		bw.close();
	}

}
