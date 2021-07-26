package codeforce.r661;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N]; int[] B = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int minA = 1000000000;
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				minA = Math.min(minA, A[i]);
			}
			st = new StringTokenizer(br.readLine(), " ");
			int minB = 1000000000;
			for (int i = 0; i < N; i++) {
				B[i] = Integer.parseInt(st.nextToken());
				minB = Math.min(minB, B[i]);
			}
			long move = 0;
			for (int i = 0; i < N; i++) {
				if (A[i] == minA && B[i] == minB) {continue;}
				// 양쪽 깍기
				int minDiff = Math.min(A[i] - minA, B[i] - minB);
				if (minDiff != 0) {
					move += minDiff;
					A[i] -= minDiff; B[i] -= minDiff;
				}
				move += A[i] - minA;
				move += B[i] - minB;
			}
			bw.write(String.valueOf(move));
			bw.newLine();
		}
		bw.close();
	}

}