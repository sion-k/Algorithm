package baekjoon.p13459;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] RandB;// 빨간 구슬, 파란 구슬 위치
	static int N;
	static int M;
	static char[][] BOARD;
	static boolean[][][][] BOOKED;
	static int[][][][] DIST;
	
	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
//	static int[] tilt(int yr, int xr, int yb, int xb, int dir) {
//		int tyr = yr + dy[dir]; int txr = xr + dx[dir];
//		int tyb = yb + dy[dir]; int txb = xb + dx[dir];
//		while (BOARD[tyr][txr] != '#' || BOARD[tyb][txb] != '#') {
//			
//		}
//	}
//	// 정점 a에서 b까지 최단 거리 반환. 도달 불가능하면 -1 반환
//	static int bfs(int[] a, int[] b) {
//		Queue<int[]> q = new LinkedList<>();
//		q.add(a); BOOKED[a[0]][a[1]][a[2]][a[3]] = true; 
//		DIST[a[0]][a[1]][a[2]][a[3]] = 0;
//		
//		while(!q.isEmpty()) {
//			int[] loc = q.poll();
//			int yr = loc[0]; int xr = loc[1];
//			int yb = loc[2]; int xb = loc[3];
//			
//			for (int next = 0; next < 4; next++) {
//				if(EDGE[here][next] && !BOOKED[next]) {
//					if (next == b) {return DIS[here] + 1;}
//					q.offer(next); BOOKED[next] = true;
//					DIS[next] = DIS[here] + 1;
//				}
//			}
//		}
//		return -1;
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		RandB = new int[4];
		BOARD = new char[N][M];
		BOOKED = new boolean[N][M][N][M];
		DIST = new int[N][M][N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				BOARD[i][j] = row.charAt(j);
				if (BOARD[i][j] == 'R') {RandB[0] = i; RandB[1] = j;}
				if (BOARD[i][j] == 'B') {RandB[2] = i; RandB[3] = j;}
			}
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.close();
	}

}
