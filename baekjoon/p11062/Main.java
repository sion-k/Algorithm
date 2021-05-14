package baekjoon.p11062;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] S; static int[] preSum;
	static int[][] cache;

	static int preSum(int i, int j) {return preSum[j + 1] - preSum[i];}

	static int dp(int i, int j) {
		if (i == j) {return S[i];}
		if (cache[i][j] != 0) {return cache[i][j];}
		int left = dp(i + 1, j); int right = dp(i, j - 1);
		if (left < right) {cache[i][j] = preSum(i, j) - left;}
		else {cache[i][j] = preSum(i, j) - right;}
		return cache[i][j];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			S = new int[N]; cache = new int[N][N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
			preSum = new int[N + 1];
			for (int i = 1; i <= N; i++) {preSum[i] = S[i - 1] + preSum[i - 1];}
			bw.write(String.valueOf(dp(0, N - 1)));
			bw.newLine();
		}
		bw.close();
	}

}