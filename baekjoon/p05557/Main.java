package baekjoon.p05557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; //항의 개수
	static int[] T;
	static long[][] cache;
	// i번째 등식을 선택하고, 지금까지 결과가 S일 때, 올바른 등식의 수 반환
	static long dp(int i, int S) {
		if (i == N - 2) {return S == T[i + 1] ? 1 : 0;}
		if (cache[i][S] != -1) {return cache[i][S];}
		long sum = 0;
		int add = S + T[i + 1]; int sub = S - T[i + 1];
		if (add <= 20) {sum += dp(i + 1, add);}
		if (sub >= 0) {sum += dp(i + 1, sub);}
		return cache[i][S] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		cache = new long[N][21];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			T[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(cache[i], -1);
		}
		System.out.println(dp(0, T[0]));
	}

}