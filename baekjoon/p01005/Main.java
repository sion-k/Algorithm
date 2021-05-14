package baekjoon.p01005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] time;
	static int[] cache;
	static ArrayList<ArrayList<Integer>> adj;

	static int dp(int i) {
		if (cache[i] != -1) {return cache[i];}
		int max = 0;
		for (int there : adj.get(i)) {max = Math.max(max, dp(there));}
		return cache[i] = max + time[i];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			time = new int[N + 1];
			cache = new int[N + 1];
			Arrays.fill(cache, -1);
			adj = new ArrayList<>(N + 1);
			adj.add(new ArrayList<>());
			for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int there = Integer.parseInt(st.nextToken());
				int here  = Integer.parseInt(st.nextToken());
				adj.get(here).add(there);
			}
			int W = Integer.parseInt(br.readLine());
			System.out.println(dp(W));
		}
	}

}