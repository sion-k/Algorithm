package baekjoon.p1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// �� �� �� �� ��
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	static int N;
	static int M;
	// ���߰� �ִ��� ������ ����
	static boolean[][] CABBAGE;
	static boolean[][] VISIT;
	
	static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < M;}
	
	// ���߰� �ִ� (y, x)���� dfs
	static void dfs(int y, int x) {
		VISIT[y][x] = true;
		for (int move = 0; move < 4; move++) {
			int ty = y + dy[move]; int tx = x + dx[move];
			// ���� �� ���̰�, ���߰� ������, �湮���� �ʾ��� ��쿡��
			if(inRange(ty, tx) && CABBAGE[ty][tx] &&!VISIT[ty][tx]) {
				dfs(ty, tx);
			}
		}
	}
	
	// �� ��ü�� dfs �ʿ��� ������������ �� ��ȯ
	static int dfsAll() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (CABBAGE[i][j] && !VISIT[i][j]) {dfs(i, j);sum++;}
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			CABBAGE = new boolean[N][M];
			VISIT = new boolean[N][M];
			int K = Integer.parseInt(st.nextToken());
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				CABBAGE[y][x] = true;
			}
			System.out.println(dfsAll());
		}
		br.close();
	}
	
}