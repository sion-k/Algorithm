package baekjoon.p02491;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); int[] S = new int[N];
		int max = 1; int inc = 1; int dec = 1;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		S[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			if (S[i - 1] < S[i]) {inc++; dec = 1;}
			else if (S[i - 1] > S[i]) {dec++; inc = 1;}
			else {inc++; dec++;}
			max = Math.max(max, inc); max = Math.max(max, dec);
		}
		System.out.println(max);
	}

}