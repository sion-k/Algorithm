package baekjoon.p14390;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] S;	
	static int[][] cache;

	static final int INF = 987654321;
	
	// i번째 행부터 타일을 채울 때 방 바닥을 모두 채우는데 필요한 타일의 최소 개수
	// b는 바로 직전 행의 칸을 가로모양 타일로 채웠는지 -> 0, 세로 모양으로 채웠는지 -> 1 나타낸다
	static int dp(int i, int b) {
		if (i == N) return 0;
		if (cache[i][b] != -1) return cache[i][b];
		return cache[i][b] = btk(i, 0, b, 0);
	}
	
	static int btk(int i, int j, int b, int nb) {
		if (j == M) return dp(i + 1, nb);
		if (S[i][j]) return btk(i, j + 1, b, nb);
		int min = INF;
		// 가로로 놓는 경우
		// 왼쪽 열에서 가로로 덮은 경우 새 타일을 덮을 필요 없이 기존에 덮은 타일을 늘리면 됨
		if (j - 1 >= 0 && !S[i][j - 1] && (nb & (1 << (j - 1))) == 0)
			min = Math.min(min, btk(i, j + 1, b, nb));
		else 
			min = Math.min(min, 1 + btk(i, j + 1, b, nb));
		// 세로로 놓는 경우
		nb |= (1 << j);
		// 위쪽 열에서 세로로 덮은 경우 새 타일을 덮을 필요 없이 기존에 덮은 타일을 늘리면 됨
		if (i - 1 >= 0 && !S[i - 1][j] && (b & (1 << j)) > 0)
			min = Math.min(min, btk(i, j + 1, b, nb));
		else
			min = Math.min(min, 1 + btk(i, j + 1, b, nb));
		return min;
	}
	
	static void rotate() {
		boolean[][] ret = new boolean[M][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				ret[j][N - i - 1] = S[i][j];
		int temp = N; N = M; M = temp;
		S = ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = row.charAt(j) == '#';
		}
		if (N < M) rotate();
		cache = new int[N][1 << M];
		for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}

}