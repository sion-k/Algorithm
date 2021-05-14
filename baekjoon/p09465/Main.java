package baekjoon.p09465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] score;
	static int[][] cache;
	
	// i - 1번째 열의 스티커를 prev를 골랐을 때 i번째 열부터 얻을 수 있는 최대 점수
	static int dp(int i, int prev) {
		if (i >= N) {return 0;}
		if (cache[i][prev] != -1) {return cache[i][prev];}
		// 위쪽인 경우 i번째 선택지는 아래 혹은 안고름
		if (prev == 0) {
			return cache[i][prev] = Math.max(score[1][i] + dp(i + 1, 1), dp(i + 1, 2));
		}
		// 아래쪽인 경우 i번째 선택지는 위 혹은 안고름
		if (prev == 1) {
			return cache[i][prev] = Math.max(score[0][i] + dp(i + 1, 0), dp(i + 1, 2));
		}
		// 안 고른 경우 i번재 선택지는 위 혹은 아래
		if (prev == 2) {
			return cache[i][prev] = Math.max(score[0][i] + dp(i + 1, 0), score[1][i] + dp(i + 1, 1));
		}
		return -10000000;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			N = Integer.parseInt(br.readLine());
			score = new int[2][N];
			cache = new int[N][3];
			for (int i = 0; i < N; i++) {Arrays.fill(cache[i], -1);}
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println(dp(0, 2));
		}
		br.close();
	}
}