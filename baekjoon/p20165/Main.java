package baekjoon.p20165;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] S; static boolean[][] F;
	static int score = 0;
	
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	static int toDir(char ch) {
		switch (ch) {
		case 'N' : return 0;
		case 'S' : return 1;
		case 'W' : return 2;
		case 'E' : return 3;
		}
		return 0;
	}
	
	static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < M;}
	
	static void fall(int sy, int sx, int d) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] booked = new boolean[N][M];
		q.offer(new int[] {sy, sx});
		booked[sy][sx] = true;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0]; int x = p[1];
			if (F[y][x]) continue;
			F[y][x] = true; // 쓰러지지 않았다면 이 지점을 쓰러뜨린다
			score++;
			int length = S[y][x];
			for (int i = 0; i < length; i++) {
				if (inRange(y, x) && !F[y][x] && !booked[y][x]) {// 쓰러지지 않았고 큐에 들어가지 않은 경우 쓰러뜨린다
					q.offer(new int[] {y, x, d});
					booked[y][x] = true;
				}
				y += dy[d]; x += dx[d];
			}
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		S = new int[N][M]; F = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = toDir(st.nextToken().charAt(0));
			fall(y - 1, x - 1, d);
			st = new StringTokenizer(br.readLine(), " ");
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			F[y - 1][x - 1] = false;
		}
		System.out.println(score);
		for (int i = 0; i < N; i++) {
			StringBuilder ans = new StringBuilder();
			for (int j = 0; j < M; j++)
				ans.append(F[i][j] ? "F" : "S").append(" ");
			System.out.println(ans.toString().trim());
		}
	}
	
}