package baekjoon.p14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] LAB;
	static boolean[][] BOOKED;
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static int bfs() {
		BOOKED = new boolean[N][M];
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {temp[i] = LAB[i].clone();}
		
		Queue<int[]> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (LAB[i][j] == 2) {
					q.add(new int[] { i, j });
					BOOKED[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 4; next++) {
				int ty = here[0] + dy[next]; int tx = here[1] + dx[next];
				if(inRange(ty, tx) && LAB[ty][tx] == 0 && !BOOKED[ty][tx]) {
					q.offer(new int[] { ty, tx });
					BOOKED[ty][tx] = true;
					LAB[ty][tx] = 2;
				}
			}
		}
		
		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (LAB[i][j] == 0) {safe++;}
			}
		}
		
		for (int i = 0; i < N; i++) {LAB[i] = temp[i].clone();}
		return safe;
	}
	
	static int max = 0;
	
	// 이전에 py, px에 벽을 세웠을 때 n개의 벽을 세우는 모든 경우의 수를 시도
	static void bfc(int py, int px, int n) {
		if (n == 0) {max = Math.max(max, bfs()); return;}
		boolean passed = false;
		if (py == -1 && px == -1) {passed = true;}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(i == py && j == px) {passed = true; continue;}
				if(passed && LAB[i][j] == 0) {
					LAB[i][j] = 1;
					bfc(i, j, n - 1);
					LAB[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		LAB = new int[N][M];
		BOOKED = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				LAB[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		bfc(-1, -1, 3);
		System.out.println(max);
	}

}