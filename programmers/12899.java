package programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _124 {
	// �ڿ����� �����ϴ� 124 �༺������ ����
	// 1 -> 1 / 2 -> 2 / 3 -> 4 / 4 -> 11
	// �ڿ��� n (5�� ����)�� �־��� �� 124�������� �ٲٴ� ���α׷��� �ۼ��Ͻÿ�

	// ���� ������ x���� ��, ���޼� ��
	static int sum(int x) {return ((int)(Math.pow(3, x)) - 1) / 2;}

	// n�� ���������� �ٲ㼭 ��ȯ (�� 2�� �׳� 3���� ǥ��)
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