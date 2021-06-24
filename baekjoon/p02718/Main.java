package baekjoon.p02718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] cache;
	
	// 4 * n 크기의 타일을 채우는 경우의 수
	// p : [0, 5) -> xxxx, ooxx, oxxo, xoox, xxoo
	static int dp(int n, int p) {
		if (n == 0) return p == 0 ? 1 : 0;
		if (n == 1) return p != 3 ? 1 : 0;
		if (cache[n][p] != -1) return cache[n][p];
		int ret = 0;
		switch (p) {
		case 0 : ret = dp(n - 1, 0) + dp(n - 1, 4) + dp(n - 1, 2) + dp(n - 1, 1) + dp(n - 2, 0); break;
		case 1 : ret = dp(n - 1, 0) + dp(n - 1, 4); break;
		case 2 : ret = dp(n - 1, 0) + dp(n - 1, 3); break;
		case 3 : ret = dp(n - 1, 2); break;
		case 4 : ret = dp(n - 1, 0) + dp(n - 1, 1); break;
		}
		return cache[n][p] = ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		cache = new int[100][5];
		for (int i = 0; i < 100; i++) Arrays.fill(cache[i], -1);
		StringBuilder ans = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) ans.append(dp(Integer.parseInt(br.readLine()), 0)).append("\n");
		System.out.print(ans);
	}

}