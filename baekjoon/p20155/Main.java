package baekjoon.p20155;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] B = new int[M + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			B[Integer.parseInt(st.nextToken())]++;
		}
		int max = 1;
		for (int i = 0; i < M + 1; i++) {
			max = Math.max(max, B[i]);
		}
		System.out.println(max);
	}

}