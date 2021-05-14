package baekjoon.p10937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N; static char[][] S;
	static int[][] cache;
	
	static final int[][] price = {{100, 70, 40},
								  { 70, 50, 30},
								  { 40, 30, 20}};
	
	static int getPrice(char a, char b) {
		if (a == 'F' || b == 'F') return 0;
		return price[a - 'A'][b - 'A'];
	}
	
	// i번째 두부모판부터 자르기 시작할 때, 사용 가능한 두부모판이 b로 주어질 때,
	// 얻을 수 있는 최대 가격
	static int dp(int i, int b) {
		if (i == N) return 0;
		if (cache[i][b] != -1) return cache[i][b];
		return cache[i][b] = btk(i, 0, b, 0);
	}

	// i번째 두부모판을 j번째 열부터 자르는 방법을 모두 시도
	// 현재 행의 사용 가능 여부는 b, 재귀호출시 넘겨줄 다음 행의 사용 가능 여부는 nb
	static int btk(int i, int j, int b, int nb) {
		if (j == N) return dp(i + 1, nb); // i번째 열을 자르는 방법을 모두 시도한 경우 재귀 호출
		if ((b & (1 << j)) > 0) return btk(i, j + 1, b, nb); // 현재 열을 사용할 수 없는 경우
		// 선택하지 않는 경우
		int max = btk(i, j + 1, b, nb);
		// 가로로 자르는 경우
		if (j + 1 < N && (b & (1 << (j + 1))) == 0)
			max = Math.max(max, getPrice(S[i][j], S[i][j + 1]) + btk(i, j + 2, b, nb));
		// 세로로 자르는 경우
		if (i + 1 < N)
			max = Math.max(max, getPrice(S[i][j], S[i + 1][j]) + btk(i, j + 1, b, nb | (1 << j)));
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new char[N][N];
		for (int i = 0; i < N; i++) S[i] = br.readLine().toCharArray();
		for (int i = 0; i < N; i++)
		cache = new int[N][1 << N];
		for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}
	
}