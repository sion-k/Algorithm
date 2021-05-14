package baekjoon.p01637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] S;
	
	// ���� ���� ���� x������ ������ ���� ��ȯ
	static long f(long x) {
		long sum = 0;
		for (int i = 0; i < N; i++)
			sum += cnt((int)x, S[i][0], S[i][1], S[i][2]);
		return sum;
	}
	
	// [A, C]���� x���� ���ų� ���� ���� ���� ��ȯ 
	static int cnt(int x, int A, int C, int B) {
		// [A, C]�� �������̰ų�, x�� A���� ���� ���� �������� ����
		if (A > C || x < A) return 0;
		C = Math.min(C, x);
		return (C - A) / B + 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");		
			for (int j = 0; j < 3; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		// f(lo) == ¦�� f(hi) == Ȧ���� hi��ȯ
		long lo = 0; long hi = Integer.MAX_VALUE;
		if (f(hi) % 2 == 0) { System.out.println("NOTHING"); System.exit(0); }
		while (lo + 1 < hi) {
			long mid = (lo + hi) / 2;
			if (f(mid) % 2 == 1) hi = mid;
			else lo = mid;
		}
		System.out.println(String.format("%d %d", hi, (f(hi) - f(hi - 1))));
	}
	
}