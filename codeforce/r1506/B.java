package codeforce.r1506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {
	static int N; static int K;
	static int tail;
	static String S;
	static int[][] cache;
	
	static final int INF = 987654321;
	// S[i]부터 *을 x로 바꾸기 시작할 때, 최소한으로 바꾸는 수
	// last는 가장 마지막으로 나온 x
	static int dp(int i, int last) {
		if (i == tail) return i - last <= K ? 0 : INF;
		if (cache[i][last] != -1) return cache[i][last];
		if (S.charAt(i) == '.') return dp(i + 1, last);
		if (i - last > K) return INF;
		// 안바꾸는 경우
		int min = dp(i + 1, last);
		// 바꾸기
		min = Math.min(min, 1 + dp(i + 1, i));
		return cache[i][last] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			S = br.readLine();
			int head = N; tail = 0;
			for (int i = 0; i < S.length(); i++)
				if (S.charAt(i) == '*') {
					head = Math.min(head, i);
					tail = Math.max(tail, i);
				}
			if (head == tail) {System.out.println(1); continue;}
			if (tail - head <= K) {System.out.println(2); continue;}
			cache = new int[N][N];
			for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
			System.out.println(2 + dp(head, head));
		}
	}

}