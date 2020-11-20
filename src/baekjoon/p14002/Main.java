package baekjoon.p14002;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] S;
	static int N;
	static int[] cache;
	static int[] choice;

	static int dp(int here) {
		if (cache[here + 1] != 0) {return cache[here + 1];}
		int max = 1; int best = 0;
		for (int next = here + 1; next < N; next++) {
			if (here == -1 || S[here] < S[next]) {
				int cand = 1 + dp(next);
				if (cand > max) {max = cand; best = next;}
			}
		}
		choice[here + 1] = best;
		return cache[here + 1] = max;
	}

	static StringBuilder ans;

	static void reconstruct(int here) {
		if (choice[here] != -1) {

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		S = new int[N]; cache = new int[N + 1];
		choice = new int[N + 1]; Arrays.fill(choice, -1);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		bw.write(String.valueOf(dp(-1) - 1));
		bw.close();
	}

}