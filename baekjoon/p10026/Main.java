package baekjoon.p10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static char[][] PICTURE;
	static boolean[][] VISIT;
	
	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	//[0, N)
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static void dfs(int y, int x) {
		VISIT[y][x] = true;
		for (int next = 0; next < 4; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if(inRange(ty, tx) && PICTURE[y][x] == PICTURE[ty][tx] && 
			!VISIT[ty][tx]) {
				dfs(ty, tx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PICTURE = new char[N][N];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				PICTURE[i][j] = row.charAt(j);
			}
		}
		br.close();
		// 적록색약이 아닌 사람
		int cnt1 = 0;
		VISIT = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!VISIT[i][j]) {
					dfs(i, j); cnt1++;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (PICTURE[i][j] == 'G') {
					PICTURE[i][j] = 'R';
				}
			}
		}
		// 적록색약
		int cnt2 = 0;
		VISIT = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!VISIT[i][j]) {
					dfs(i, j); cnt2++;
				}
			}
		}
		System.out.println(cnt1 + " " + cnt2);
	}

}