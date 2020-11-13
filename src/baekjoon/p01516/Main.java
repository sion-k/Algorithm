package baekjoon.p01516;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] t;
	static List<List<Integer>> adj;
	static int[] cache;

	// i번째 건물을 짓는데 걸리는 최소 시간
	static int dp(int i) {
		if (cache[i] != 0) {return cache[i];}
		int max = 0;
		for (int there : adj.get(i)) {max = Math.max(max, dp(there));}
		return cache[i] = t[i] + max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		t = new int[N + 1];
		adj = new ArrayList<>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			t[i] = time;
			adj.add(new ArrayList<>());
			int prev = 0;
			while ((prev = Integer.parseInt(st.nextToken())) != -1) {
				adj.get(i).add(prev);
			}
		}
		cache = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			bw.write(String.valueOf(dp(i)));
			bw.newLine();
		}
		bw.close();
	}

}