package baekjoon.p2156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] W;
	static int[] cache;
	
	// i번째 잔을 마셨을 때 나머지 잔을 마셔서 얻을 수 있는 최대값
	static int dp(int i) {
		if (i >= N) {return 0;}
		if (i  == N - 1) {return W[i];}
		if (cache[i] != -1) { return cache[i];}
		int max = Math.max(W[i + 1] + dp(i + 3), dp(i + 2));
		return cache[i] = W[i] + Math.max(max, dp(i + 3));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N];
		cache = new int[N];
		Arrays.fill(cache, -1);
		
		for (int i = 0; i < N; i++) {W[i] = Integer.parseInt(br.readLine());}
		
		br.close();
		System.out.println(Math.max(dp(0), dp(1)));
	}

}