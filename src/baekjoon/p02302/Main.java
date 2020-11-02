package baekjoon.p02302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static boolean[] VIP;
	static int[] cache;

	static int dp(int i) {
		if (i >= N) {return 1;}
		if (cache[i] != -1) {return cache[i];}
		int sum = 0;
		if (!VIP[i] && !VIP[i + 1]) {sum += dp(i + 2);}
		sum += dp(i + 1);
		return cache[i] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N + 1]; Arrays.fill(cache, -1);
		VIP = new boolean[N + 1];
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			VIP[Integer.parseInt(br.readLine())] = true;
		}
		System.out.println(dp(1));
	}

}