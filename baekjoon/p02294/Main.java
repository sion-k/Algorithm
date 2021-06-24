package baekjoon.p02294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] COIN;
	static int[] cache;

	// k원을 만드는데 필요한 최소의 동전의 개수 반환
	static int dp(int k) {
		if (k == 0) {return 0;}
		if (cache[k] != 0) {return cache[k];}
		int min = 10001;
		for (int i = 0; i < N; i++) {
			int left = k - COIN[i];
			if (left >= 0) {min = Math.min(min, dp(left));}
		}
		return cache[k] = 1 + min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		COIN = new int[N]; cache = new int[10001];
		for (int i = 0; i < N; i++) {COIN[i] = Integer.parseInt(br.readLine());}
		br.close();
		int ret = dp(k);
		if (ret > 10000) {ret = -1;}
		System.out.println(ret);
	}

}