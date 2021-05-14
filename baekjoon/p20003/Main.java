package baekjoon.p20003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// A / B �м� ���¸� ����� ���� ��ȯ
	static long[] reduct(long a, long b) {
		long fac = gcd(a, b);
		long[] ret = new long[2];
		ret[0] = a / fac; ret[1] = b / fac;
		return ret;
	}

	static long gcd(long a, long b) {
		if (b == 0) {return a;}
		return gcd(b, a % b);
	}

	static long gcd(long[] term) {
		long ret = term[0];
		for (int i = 1; i < term.length; i++) {
			ret = gcd(ret, term[i]);
		}
		return ret;
	}

	static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

	static long lcm(long[] term) {
		long ret = term[0];
		for (int i = 1; i < term.length; i++) {
			ret = lcm(ret, term[i]);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] A = new long[N]; // ���ڵ��� �迭
		long[] B = new long[N]; // �и���� �迭
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long[] r = reduct(a, b);
			A[i] = r[0]; B[i] = r[1];
		}
		br.close();
		// �и���� �ּҰ������ �и� ���
		long lcmB = lcm(B);
		for (int i = 0; i < N; i++) {A[i] *= (lcmB / B[i]);}
		// ��е� �м����� ������ �ִ������� ���Ѵ�
		long gcdA = gcd(A);
		long[] r = reduct(gcdA, lcmB);
		System.out.println(r[0] + " " + r[1]);
	}

}