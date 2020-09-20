package baekjoon.p17179;

import java.util.*;
import java.io.*;

public class Main {
	private static double[] S;

	private static int getMid(double start, double end) {
		double mean = (start + end) / 2;
		int mid = Arrays.binarySearch(S, mean);
		if (mid < 0) {
			mid = mid * -1 - 1;
			double leftDiff = mean - S[mid - 1];
			double rightDiff = S[mid] - mean;
			if (Double.compare(leftDiff, rightDiff) == -1) {
				mid--;
			}
		}
		return mid;
	}

	private static int cut(int start, int end, int N) {
		if (N == 0) {
			return (int) (S[end] - S[start]);
		}
		if (end - start - 1 < N) {
			return -1;
		}
		int mid = getMid((double) start, (double) end);
		int max = -1;
		N--;
		for (int i = 0; i <= N; i++) {
			int left = cut(start, mid, i);
			int right = cut(mid, end, N - i);
			if (left != -1 && right != -1) {
				int minFrac = Math.min(left, right);
				max = Math.max(max, minFrac);
			}
		}
		return max;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		S = new double[M + 2];
		S[0] = 0;
		S[M + 1] = L;
		for (int i = 1; i < M + 1; i++) {
			S[i] = (double) Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			String output = cut(0, M + 1, Integer.parseInt(br.readLine())) + "";
			bw.write(output);
			bw.newLine();
		}
		br.close();
		bw.close();

		/*
		 * Scanner sc = new Scanner(System.in); while(true) {
		 * System.out.println(getMid((double)sc.nextInt(), (double)sc.nextInt())); }
		 */
	}

}
