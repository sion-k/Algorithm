package baekjoon.p02908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int[] pow = {100, 10, 1};

	static int reverse(int x) {
		int[] digit = new int[3];
		for (int i = 0; x != 0; i++, x /= 10) {
			digit[i] = x % 10;
		}
		int sum = 0;
		for (int i = 2; i >= 0; i--) {
			sum += (digit[i] * pow[i]);
		}
		return sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		A = reverse(A); B = reverse(B);
		System.out.println(A > B ? A : B);
	}

}