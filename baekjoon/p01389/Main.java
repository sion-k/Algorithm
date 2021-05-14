package baekjoon.p01389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; //유저의 수
	static boolean[][] EDGE;
	static boolean[] BOOKED;
	
	// 정점 from에서 나머지 모든 정점까지의 최단거리를 담은 DIST 반환
	static int[] bfs(int from) {
		int[] dist = new int[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(from); BOOKED[from] = true; dist[from] = 0;
		
		while(!q.isEmpty()) {
			int here = q.poll();
			for (int next = 1; next <= N; next++) {
				if(EDGE[here][next] && !BOOKED[next]) {
					q.offer(next); BOOKED[next] = true;
					dist[next] = dist[here] + 1;
				}
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		EDGE = new boolean[N + 1][N + 1];
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			EDGE[from][to] = EDGE[to][from] = true;
		}
		
		int[] kevin = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			BOOKED = new boolean[N + 1];
			int[] dist = bfs(i); int sum = 0;
			for (int d : dist) {sum += d;}
			kevin[i] = sum;
		}
		
		int min = 1; // 케빈 베이컨 수가 가장 적은 사람 인덱스
		for (int i = 2; i <= N; i++) {
			if (kevin[i] < kevin[min]) {min = i;}
		}
		System.out.println(min);
	}

}