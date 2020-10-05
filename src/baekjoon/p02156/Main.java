package baekjoon.p02156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] W;
	static int[][] cache;
	
	// i번째 잔을 마셨을 때 나머지 잔을 마셔서 얻을 수 있는 최대값
	// 0 : 둘다 마심 1 : 첫 번째 잔만 마심 2 : 둘다 안 마심
	static int dp(int i, int prev) {
		if (i >= N) {return 0;}
		if (i  == N - 1 && prev != 0) {return W[i];}
		if (cache[i][prev] != -1) { return cache[i][prev];}
		if (prev == 0) {
			return cache[i][prev] = dp(i + 1, 1);
		}
		if (prev == 1) {
			return cache[i][prev] = 
			W[i] + Math.max(W[i + 1] + dp(i + 2,  0), dp(i + 2,  1));
		}
		if (prev == 2) {
			return cache[i][prev] = 
			W[i] + Math.max(W[i + 1] + dp(i + 3, 1), dp(i + 2, 1));
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N];
		cache = new int[N][3];
		for (int i = 0; i < N; i++) {
			Arrays.fill(cache[i], -1);
		}
		for (int i = 0; i < N; i++) {W[i] = Integer.parseInt(br.readLine());}
		
		br.close();
		System.out.println(dp(0, 2));
	}

}