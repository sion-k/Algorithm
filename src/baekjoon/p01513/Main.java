package baekjoon.p01513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, C;
	// (y, x)위치에 오락실에 몇번째 오락실이 존재하는지 저장, 없으면 0
	static int[][] MAP;
	static int[][][] cache;

	static final int MOD = 1000007;

	static boolean inRange(int y, int x) {
		return 1 <= y && y <= N && 1 <= x && x <= M;
	}

	// (y, x)에서 마지막으로 k번째 오락실을 방문했을 때,
	// (N, M)까지 C개의 오락실을 방문하는 경로의 수를 반환
	static int dp(int y, int x, int k) {

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		MAP = new int[N + 1][M + 1];
		for (int i = 1; i <= C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			MAP[y][x] = i;
		}

	}

}