package baekjoon.p01351;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static long N; static int P; static int Q;
	static Map<Long, Long> cache = new HashMap<>();

	static long dp(long i) {
		if (i == 0) return 1;
		if (cache.containsKey(i)) return cache.get(i);
		cache.put(i, dp(i / P) + dp(i / Q));
		return cache.get(i);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		System.out.println(dp(N));
	}

}