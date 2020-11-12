package codeforce.r674;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {
	static int[] AWin = { 1, 2, 0 };
	static int[] BWin = { 2, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[3]; int[] B = new int[3];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {A[i] = Integer.parseInt(st.nextToken());}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {B[i] = Integer.parseInt(st.nextToken());}
		//int[] tempA = A.clone(); int[] tempB = B.clone();

		int maxAWin = 0;
		for (int i = 0; i < 3; i++) {
			maxAWin += Math.min(A[i], B[AWin[i]]);
		}
		int minBWin = 0;
		for (int i = 0; i < 3; i++) {
			int diff = Math.abs(A[i] - B[i]);
			A[i] = 0; B[i] = diff;
			diff = Math.abs(A[AWin[i]] - B[i]);
			A[i] = 0; B[i] = diff;
		}
		System.out.println(Arrays.toString(A));
		System.out.println(Arrays.toString(B));
		for (int i = 0; i < 3; i++) {
			minBWin = Math.max(minBWin, B[i]);
		}
		System.out.print(minBWin + " ");
		System.out.println(maxAWin);
	}

}