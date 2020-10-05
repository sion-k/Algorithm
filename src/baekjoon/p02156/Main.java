package baekjoon.p02156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] W;
	static int[][] cache;
	
	// i��° ���� ������ �� ������ ���� ���ż� ���� �� �ִ� �ִ밪
	// 0 : �Ѵ� ���� 1 : ù ��° �ܸ� ���� 2 : �Ѵ� �� ����
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