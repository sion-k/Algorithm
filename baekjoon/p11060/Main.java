package baekjoon.p11060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A;
	static int[] cache;

	// i번째 칸[1, N)에 있을 때 오른쪽 끝까지 걸리는 점프 최소 횟수
	static int dp(int i) {
		if (A[i] == 0) {return 1000;}
		if (i + A[i] >= N) {return 1;}
		if (cache[i] != 0) {return cache[i];}
		int min = 1000;
		for (int range = 1; range <= A[i]; range++) {
			min = Math.min(min, dp(i + range));
		}
		return cache[i] = 1 + min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1]; cache = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {A[i] = Integer.parseInt(st.nextToken());}
		br.close();
		if (N == 1) {System.out.println(0);}
		else {
			int ret = dp(1);
			if (ret >= 1000) {ret = -1;}
			System.out.println(ret);
		}
	}

}