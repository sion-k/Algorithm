package baekjoon.p07579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] C; static int[] M;
	static int[][] cache;

	// i번째 앱을 선택해야 할 때, c비용 만큼 소모할 수 있을 때의 최대 획득 가능 바이트
	static int dp(int i, int c) {
		if (i >= N) {return 0;}
		if (cache[i][c] != -1) {return cache[i][c];}
		// i번째를 취소 안하는 경우
		cache[i][c] = dp(i + 1, c);
		// i번째를 취소 하는 경우
		if (c >= C[i]) {
			cache[i][c] = Math.max(cache[i][c], dp(i + 1, c - C[i]) + M[i]);
		}
		return cache[i][c];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		C = new int[N]; M = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {M[i] = Integer.parseInt(st.nextToken());}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {C[i] = Integer.parseInt(st.nextToken());}

		cache = new int[N][10001];
		for (int i = 0; i < N; i++) {Arrays.fill(cache[i], -1);}

		for (int c = 0; c <= 10000; c++) {
			if (dp(0, c) >= K) {System.out.println(c); break;}
		}

	}

}