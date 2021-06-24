package baekjoon.p11722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S;
	static int[] cache;
	
	// start에서 시작하는 감소 부분 수열 중 가장 긴 길이 반환
	static int lds(int start) {
		if (cache[start] != 0) {return cache[start];}
		int ret = 1;
		for (int next = start + 1; next < N; next++) {
			if (S[next] < S[start]) {ret = Math.max(ret,  1 + lds(next));}
		}
		return cache[start] = ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N]; cache = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		int max = 1;
		for (int i = 0; i < N; i++) {max = Math.max(max, lds(i));}
		System.out.println(max);
	}

}