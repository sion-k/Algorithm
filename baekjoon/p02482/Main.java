package baekjoon.p02482;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][][] cache;
	static final int MOD = 1000000003;

	// 색상환을 일자로 쭉 펴서 n개의 배열로 생각했을 때
	// 첫번째 색을 선택할 수 있거나 없을 때 (p ? 1 : 0)
	// k개를 선택하는 경우의 수
	static int dp(int p, int n, int k) {
		// 색상환의 크기가 0이면 전부 고른 경우에만 경우의 수를 하나 찾음
		if (n == 0) {return k == 0 ? 1 : 0;}
		// 추가적인 최적화 : n개의 배열에서 최대 n / 2 + 1개를 고를 수 있음
		if (k > n / 2 + 1) {return 0;}
		// 답이 0일수도 있기 때문에 -1로 초기화
		if (cache[p][n][k] != -1) {return cache[p][n][k];}
		// 맨 앞칸을 고르는 경우
		int sum = dp(0, n - 1, k);
		// 이전에 고르지지 않았고, 골라야 할 개수가 남은 경우
		if (p == 0 && k > 0) {sum = (sum + dp(1, n - 1, k - 1)) % MOD;}
		return cache[p][n][k] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		cache = new int[2][N + 1][K + 1];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < N + 1; j++)
				Arrays.fill(cache[i][j], -1);
		// 색상환을 일렬로 펴서 생각하면 다음과같은 3가지 경우의 수
		// 양 끝은 둘다 선택할 수 없다
		// 1. OX.....X
		// 2. X.....XO
		// 3. X......X
		// 1번 2번 경우
		int sum = (2 * dp(0, N - 3, K - 1)) % MOD;
		// 3번 경우
		sum = (sum + dp(0, N - 2, K)) % MOD;
		System.out.println(sum);
	}

}