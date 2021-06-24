package baekjoon.p02631;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N; static int[] S;
	static int[] cache;

	// start에서 시작하는 LIS의 길이 반환
	static int dp(int start) {
		if (cache[start + 1] != 0) {return cache[start + 1];}
		int max = 1;
		for (int next = start + 1; next < N; next++) {
			if (start == - 1 || S[next] > S[start]) {
				max = Math.max(max, 1 + dp(next));
			}
		}
		return cache[start + 1] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(br.readLine());}
		cache = new int[N + 1];
		System.out.println(N - (dp(-1) - 1));
	}

}