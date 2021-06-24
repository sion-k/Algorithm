package baekjoon.p02573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] ICEBERG;
	static boolean[][] VISIT;
	
	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	//[0, N)
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	// 빙산을 1년 경과 시킨다
	static void melt() {
		int[][] temp = new int[N][M]; // 이전의 모습 보관
		for (int i =0 ; i <N; i++) {temp[i] = ICEBERG[i].clone();}
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (temp[y][x] == 0) {continue;}
				int cnt = 0;
				// 인접한 0의 개수 파악
				for (int adj = 0; adj < 4; adj++) {
					int ty = y + dy[adj]; int tx = x + dx[adj];
					if(inRange(ty, tx) && temp[ty][tx] == 0) {
						cnt++;
					}
				}
				ICEBERG[y][x] -= cnt;
				if (ICEBERG[y][x] < 0) {ICEBERG[y][x] = 0;}
			}
		}
	}
	
	// 전체에 대해 dfs(y, x)가 호출된 횟수 반환 (덩어리의 수)
	static int dfsAll() {
		VISIT = new boolean[N][M];
		int cnt = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if(ICEBERG[y][x] != 0 && !VISIT[y][x]) {
					dfs(y, x);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void dfs(int y, int x) {
		VISIT[y][x] = true;
		for (int next = 0; next < 4; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if(inRange(ty, tx) && ICEBERG[ty][tx] != 0 && !VISIT[ty][tx]) {
				dfs(ty, tx);
			}
		}
	}
	
	static int solve() {
		int piece = 1; // 제일 처음에는 한 덩어리의 빙산
		int year = 0;
		while((piece = dfsAll()) != 0) {
			if(piece >= 2) {return year;}
			melt(); year++;
		}
		return piece;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ICEBERG = new int[N][M];
		VISIT = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				ICEBERG[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		System.out.println(solve());
	}
}
