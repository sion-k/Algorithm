package baekjoon.p20003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// A / B 분수 형태를 통분한 것을 반환
	static int[] reduct(int a, int b) {
		int fac = 0;
		if (a < b) {fac = gcd(b, a);}
		else {fac = gcd(a, b);}
		int[] ret = new int[2];
		ret[0] = a / fac; ret[1] = b / fac;
		return ret;
	}
	// a > b여야한다
	static int gcd(int a, int b) {
		if (b == 0) {return a;}
		return gcd(b, a % b);
	}
	static int gcd(int[] t) {
		int ret = 1;
		for (int i = 0; i < t.length; i++) {
			if (ret < t[i]) {
				int temp = ret;
				ret = t[i];
				t[i] = temp;
			}
			ret = gcd(ret, t[i]);
		}
		return ret;
	}
	// a > b여야한다
	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
	static int lcm(int[] t) {
		int ret = t[0];
		for (int i = 1; i < t.length; i++) {
			if (ret < t[i]) {
				int temp = ret;
				ret = t[i];
				t[i] = temp;
			}
			ret = lcm(ret, t[i]);
		}
		return ret;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N]; // 분자들의 배열
		int[] B = new int[N]; // 분모들의 배열
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int[] r = reduct(a, b);
			A[i] = r[0]; B[i] = r[1];
		}
		// 분모들의 최소공배수로 분모를 통분
		int lcmB = lcm(B);
		for (int i = 0; i < N; i++) {
			A[i] = A[i] * (lcmB / B[i]);
		}
		// 통분된 분수들의 분자의 최대공약수를 구한다
		int gcdA = gcd(A);
		int[] r = reduct(gcdA, lcmB);
		System.out.println(r[0] + " " + r[1]);
		br.close();
	}

}