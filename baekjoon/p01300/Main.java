package baekjoon.p01300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	
	// A에서 x보다 작거나 같은 수의 개수
	static long f(int x) {
		long sum = 0;
		int length = Math.min(N, x);
		for (int i = 1; i <= length; i++) sum += Math.min((long)N * i, x) / i;
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int lo = 0; int hi = 1000000001;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (f(mid) < K) lo = mid;
			else hi = mid;
		}
		System.out.println(hi);
	}

}