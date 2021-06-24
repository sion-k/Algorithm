package baekjoon.p09655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static final String[] CYSK = {"CY", "SK"};
	static int N;
	static int[] cache;

	// [1 , N]번째 돌에서 선택해야 할 때 이길 수 있는지 여부
	static int dp(int i) {
		if (i > N || i == N - 1) {return 0;}
		if (i == N - 2 || i == N) {return 1;}
		if (cache[i] != -1) {return cache[i];}
		int choice1 = dp(i + 2) + dp(i + 4);
		int choice3 = dp(i + 4) + dp(i + 6);
		if (choice1 == 2 || choice3 == 2) {return cache[i] = 1;}
		return cache[i] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N + 1]; Arrays.fill(cache, -1);
		System.out.println(CYSK[dp(1)]);
	}

}