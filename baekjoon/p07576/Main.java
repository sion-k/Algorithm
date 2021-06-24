package baekjoon.p07576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 행
	static int M; // 열
	static int[][] TOMATO;
	static int[][] DAY;
	static boolean[][] BOOKED;
	
	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	// 토마토들이 모두 익었는지 반환
	static boolean isAllRipe() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(TOMATO[i][j] == 0) {return false;}
			}
		}
		return true; 
	}
	
	// 정점 start에서 bfs한 최대 거리 반환
	static int bfsAll() {
		if(isAllRipe()) {return 0;}
		Queue<int[]> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(TOMATO[i][j] == 1) {
					q.add(new int[] {i, j});
					BOOKED[i][j] =true;
				}
			}
		}
		int maxDay = 0;
		while(!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 4; next++) {
				int ty = here[0] + dy[next]; int tx = here[1] + dx[next];
				if(inRange(ty, tx) && TOMATO[ty][tx] == 0 && !BOOKED[ty][tx]) {
					q.offer(new int[] {ty, tx});
					BOOKED[ty][tx] = true;
					TOMATO[ty][tx] = 1;
					DAY[ty][tx] = DAY[here[0]][here[1]] + 1;
					maxDay = Math.max(maxDay, DAY[ty][tx]);
				}
			}
		}
		if(isAllRipe()) {return maxDay;} 
		else {return -1;}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		TOMATO = new int[N][M];
		DAY = new int[N][M];
		BOOKED = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				TOMATO[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfsAll());
	}

}