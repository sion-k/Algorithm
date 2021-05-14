package baekjoon.p01037;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] S = new int[N];
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(S);
		System.out.println(S[0] * S[N - 1]);
	}
	
}