package baekjoon.p01648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] cache;
	
	static final int MOD = 9901;
	
	static int dp(int i, int f) {
		if (i == M) return f == 0 ? 1 : 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, 0);
	}
	
	// i번째 열을 j번째 행부터 완전히 채우는 경우를 모두 시도한다
	// 단 현재 이미 채워져 있는 칸은 p로 나타내고
	// 모두 채운경우 dp(i + 1, f)를 통해 답을 구해서 반환한다
	static int btk(int i, int j, int p, int f) {
		if (j == N) return dp(i + 1, f);
		// 이미 채워져 있는 열일 경우 다음으로 넘어간다
		if ((p & (1 << j)) > 0) return btk(i, j + 1, p, f);
		// 가로로 설치하는 경우
		int sum = btk(i, j + 1, p, f | (1 << j));
		// 세로로도 설치할 수 있는 경우
		if (j + 1 < N && (p & (1 << (j + 1))) == 0)
			sum = (sum + btk(i, j + 2, p, f)) % MOD ;
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cache = new int[M][(int)(Math.pow(2, N))];
		for (int i = 0; i < M; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}
	
}