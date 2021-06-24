package baekjoon.p02644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 정점 수
	static int M; // 간선 수
	static boolean[][] EDGE;// 인접 행렬 표현법
	static boolean[] BOOKED;// BFS용
	static int[] DIS;
	
	// 정점 from에서 to까지 최단 거리 반환. 도달 불가능하면 -1 반환
	static int bfs(int from, int to) {
		Queue<Integer> q = new LinkedList<>();
		q.add(from); BOOKED[from] = true; DIS[from] = 0;
		
		while(!q.isEmpty()) {
			int here = q.poll();
			for (int next = 1; next <= N; next++) {
				if(EDGE[here][next] && !BOOKED[next]) {
					if (next == to) {return DIS[here] + 1;}
					q.offer(next); BOOKED[next] = true;
					DIS[next] = DIS[here] + 1;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		BOOKED = new boolean[N + 1];
		EDGE = new boolean[N + 1][N + 1];
		DIS = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			EDGE[a][b] = EDGE[b][a] = true;
		}
		System.out.println(bfs(from, to));
		br.close();
	}

}