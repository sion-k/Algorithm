package baekjoon.p01026;

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
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {A[i] = Integer.parseInt(st.nextToken());}
		st = new StringTokenizer(br.readLine(), " ");
		int[] B = new int[N];
		for (int i = 0; i < N; i++) {B[i] = Integer.parseInt(st.nextToken());}
		Arrays.sort(A); Arrays.sort(B);
		int sum = 0;
		for (int i = 0; i < N; i++) {sum += (A[i] * B[N - i - 1]);}
		System.out.println(sum);
	}

}