package codeforce.r677.E;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] cache;

	static int comb(int n, int r) {
		if (r == 0 || n == r) {return 1;}
		if (cache[n][r] != 0) {return cache[n][r];}
		return cache[n][r] = (comb(n - 1, r - 1) + comb(n - 1, r));
	}

	static long fac(int N) {
		if (N == 0) {return 1;}
		return N * fac(N - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		cache = new int[N + 1][N + 1];
		long ret = comb(N, N / 2) * ((long)Math.pow(fac(N / 2 - 1), 2)) / 2;
		bw.write(String.valueOf(ret));
		bw.newLine();
		bw.close();
	}

}