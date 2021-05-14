package baekjoon.p01654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K; static int[] S;

	static long binSearch() {
		long lo = 0;
		long hi = (long)Integer.MAX_VALUE + 1;
		while (lo + 1 < hi) {
			long mid = (lo + hi) / 2;
			if (get(mid))
				lo = mid;
			else
				hi = mid;
		}
		return lo;
	}

	// ������ ���̸� l�� �� ��, �� ���̷� N�� �̻��� ������ ���� �� �ִ��� ����
	static boolean get(long l) {
		long cnt = 0;
		for (int i = 0; i < K; i++)
			if ((cnt += (S[i] / l)) >= N)
				return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		S = new int[K];
		for (int i = 0; i < K; i++)
			S[i] = Integer.parseInt(br.readLine());
		System.out.println(binSearch());
	}

}