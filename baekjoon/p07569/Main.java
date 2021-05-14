package baekjoon.p07569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 행
	static int M; // 열
	static int H; // 높이
	static int[][][] TOMATO;
	static int[][][] DAY;
	static boolean[][][] BOOKED;
	
	// 전후좌우상하
	static int[] dy = { -1, 1, 0, 0, 0, 0 };
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x, int z) {
		return 0 <= y && y < N && 0 <= x && x < M && 0 <= z && z < H;
	}
	
	// 토마토들이 모두 익었는지 반환
	static boolean isAllRipe() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < H; k++) {
					if(TOMATO[i][j][k] == 0) {return false;}					
				}
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
				for (int k = 0; k < H; k++) {
					if(TOMATO[i][j][k] == 1) {
						q.add(new int[] {i, j, k});
						BOOKED[i][j][k] = true;
					}
				}
				
			}
		}
		int maxDay = 0;
		while(!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 6; next++) {
				int ty = here[0] + dy[next]; 
				int tx = here[1] + dx[next];
				int tz = here[2] + dz[next];
				if(inRange(ty, tx, tz) && TOMATO[ty][tx][tz] == 0 && !BOOKED[ty][tx][tz]) {
					q.offer(new int[] {ty, tx, tz});
					BOOKED[ty][tx][tz] = true;
					TOMATO[ty][tx][tz] = 1;
					DAY[ty][tx][tz] = DAY[here[0]][here[1]][here[2]] + 1;
					maxDay = Math.max(maxDay, DAY[ty][tx][tz]);
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
		H = Integer.parseInt(st.nextToken());
		TOMATO = new int[N][M][H];
		DAY = new int[N][M][H];
		BOOKED = new boolean[N][M][H];
		for (int k = H - 1; k >= 0; k--) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					TOMATO[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		System.out.println(bfsAll());
	}

}