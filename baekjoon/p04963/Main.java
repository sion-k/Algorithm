package baekjoon.p04963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int H; // 높이
	static int W; // 너비
	static boolean[][] LAND;
	static boolean[][] VISIT;
	
	// 왼쪽 위부터 가운데를 제외하고 8개의 이동
	static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < H && 0 <= x && x < W;
	}
	
	// dfs(here)의 호출 횟수 반환
	static int dfsAll() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (LAND[i][j] && !VISIT[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void dfs(int y, int x) {
		VISIT[y][x] = true;
		for (int next = 0; next < 8; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if (inRange(ty, tx) && LAND[ty][tx] && !VISIT[ty][tx]) {
				dfs(ty, tx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while (!(line = br.readLine()).equals("0 0")) {
			StringTokenizer st = new StringTokenizer(line, " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			LAND = new boolean[H][W];
			VISIT = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					LAND[i][j] = st.nextToken().equals("1");
				}
			}
			System.out.println(dfsAll());
			
		}
		br.close();
		
	}

}