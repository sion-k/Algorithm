package baekjoon.p11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S;
	static int[] ldsCache;
	static int[] btnCache;

	// start이후에서 시작하는 감소 부분 수열 중 가장 긴 길이 반환
	static int LDS(int start) {
		if (ldsCache[start] != 0) {return ldsCache[start];}
		int ret = 1;
		for (int next = start + 1; next < N; next++) {
			if (S[next] < S[start]) {ret = Math.max(ret,  1 + LDS(next));}
		}
		return ldsCache[start] = ret;
	}

	static int BTN(int start) {
		if (btnCache[start] != 0) {return btnCache[start];}
		int max = LDS(start);
		for (int next = start + 1; next < N; next++) {
			if (S[start] < S[next]) {max = Math.max(max, 1 + BTN(next));}
		}
		return btnCache[start] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		ldsCache = new int[N]; btnCache = new int[N];
		int max = BTN(0);
		for (int i = 1; i < N; i++) {max = Math.max(max, BTN(i));}
		System.out.println(max);
	}

}