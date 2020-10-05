package baekjoon.p02178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int y; int x;
		public Pos(int y, int x) {
			this.y = y; this.x = x;
		}
	}
	
	// 상 하 좌 우 순
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static int N;
	static int M;
	
	static boolean[][] MAZE;
	static boolean[][] BOOKED;
	static int[][] DIS;
	
	static boolean inRange(int y, int x) {return 1 <= y && y <= N && 1 <= x && x <= M;}
	
	// (1, 1)에서 bfs, 최단 거리를 반환한다
	static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(1, 1));
		BOOKED[1][1] = true;
		DIS[1][1] = 1;
		
		while(!q.isEmpty()) {
			Pos here = q.poll();
			for (int move = 0; move < 4; move++) {
				int ty = here.y + dy[move]; int tx = here.x + dx[move];
				if(inRange(ty, tx) && MAZE[ty][tx] && !BOOKED[ty][tx]) {
					q.offer(new Pos(ty, tx));
					BOOKED[ty][tx] = true;
					DIS[ty][tx] = DIS[here.y][here.x] + 1;
				}
			}
		}
		return DIS[N][M];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		MAZE = new boolean[N + 1][M + 1];
		BOOKED = new boolean[N + 1][M + 1];
		DIS = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				MAZE[i][j + 1] = (line[j] == '1') ? true : false;
			}
		}
		br.close();
		
		System.out.println(bfs());
	}

}