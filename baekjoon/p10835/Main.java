package baekjoon.p10835;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A; static int[] B;
	static int[][] cache;

	// 맨 위의 카드가 각각 i, j번째 카드일 때 얻을 수 있는 최대 점수
	static int dp(int i, int j) {
		if (i >= N || j >= N) {return 0;}
		if (cache[i][j] != -1) {return cache[i][j];}
		// 1. 왼쪽 카드만 통에 버리는 경우
		cache[i][j] = dp(i + 1, j);
		// 2. 둘 다 버리는 경우
		cache[i][j] = Math.max(cache[i][j], dp(i + 1, j + 1));
		// 3. 오른쪽 카드만 버려서 점수를 얻을 수 있는 경우
		if (A[i] > B[j]) {
			cache[i][j] = Math.max(cache[i][j], B[j] + dp(i, j + 1));
		}
		return cache[i][j];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N]; B = new int[N];
		cache = new int[N][N];
		for (int i = 0; i < N; i++) {Arrays.fill(cache[i], -1);}
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {A[i] = Integer.parseInt(st.nextToken());}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {B[i] = Integer.parseInt(st.nextToken());}
		br.close();
		System.out.println(dp(0, 0));
	}

}