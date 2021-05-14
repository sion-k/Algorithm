package baekjoon.p02805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S;

	static int binSearch(int x) {
		int lo = -1;
		int hi = 1000000001;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (chop(mid) < x)
				hi = mid;
			else
				lo = mid;
		}
		return lo;
	}

	static long chop(int height) {
		long sum = 0;
		for (int i = 0; i < N; i++)
			if (S[i] > height)
				sum += (S[i] - height);
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		S = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		System.out.println(binSearch(M));
	}

}