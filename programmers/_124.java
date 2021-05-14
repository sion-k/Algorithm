package programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _124 {
	// 자연수만 존재하는 124 행성에서의 진법
	// 1 -> 1 / 2 -> 2 / 3 -> 4 / 4 -> 11
	// 자연수 n (5억 이하)가 주어질 때 124진법으로 바꾸는 프로그램을 작성하시오

	// 항의 개수가 x개일 때, 등비급수 합
	static int sum(int x) {return ((int)(Math.pow(3, x)) - 1) / 2;}

	// n을 삼진법으로 바꿔서 반환 (단 2는 그냥 3으로 표현)
	static char[] ternary(int n) {
		StringBuilder sb = new StringBuilder();
		while (n >= 3) {
			int r = n % 3;
			sb.append(r == 2 ? 3 : r);
			n /= 3;
		}
		sb.append(n == 2 ? 3 : n);
		return sb.reverse().toString().toCharArray();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int N = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 100; tc++) {
		int N = tc;
		int x = 1;
		while (sum(x) <= N) x++;
		x--;
		char[] digit = new char[x];
		Arrays.fill(digit, '1');
		N -= sum(x);
		char[] tn = ternary(N);
		for (int i = 0; i < tn.length; i++) digit[x - 1 - i] += (tn[tn.length - 1 - i] - '0');
		for (char d : digit) System.out.print(d);
		System.out.println();
		}
	}

}