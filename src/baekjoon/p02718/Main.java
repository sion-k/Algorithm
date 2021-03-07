package baekjoon.p02718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] cache;
	
	// 4 * n 크기의 타일을 채우는 경우의 수, 그런데 타일의 위 두개 혹은 아래 두개가 막혀있을 수 있음
	// p = 0 안막힘, p = 1 위에 두개, p = 2 아래 두개
	static int dp(int n, int p) {
		if (n == 0 || n == 1) return 1;
		if (cache[n][p] != -1) return cache[n][p];
		if (p == 0) return cache[n][p] = dp(n - 1, 0) + dp(n - 1, 1) + dp(n - 1, 2) + 2 * dp(n - 2, 0);
		else return cache[n][p] = dp(n - 1, 0) + dp(n - 1, 3 - p);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		cache = new int[100][3];
		for (int i = 0; i < 100; i++) Arrays.fill(cache[i], -1);
		StringBuilder ans = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) ans.append(dp(Integer.parseInt(br.readLine()), 0)).append("\n");
		System.out.print(ans);
	}

}