package baekjoon.p1744;

import java.io.*;
import java.util.Arrays;

public class Main {
	private static int[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		S = new int[N];
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		Arrays.sort(S);

		// [0, f)�� �̹� ��ȸ�� ����
		int f = 0;
		long sum = 0;
		while (f + 1 < N && S[f] < 0 && S[f + 1] < 0) {
			sum += (S[f] * S[f + 1]);
			f += 2;
		}
		// ¦ �������� ���� ������ 0�� �����ش�
		if (f + 1 < N && S[f] < 0 && S[f + 1] == 0) {
			f += 2;
		}

		// (b, N - 1]�� �̹� ��ȸ�� ����
		int b = N - 1;
		while (f <= b - 1 && S[b - 1] > 1 && S[b] > 1) {
			sum += (S[b - 1] * S[b]);
			b -= 2;
		}
		
		// ��ȸ�ϸ鼭 ���� ���� [f, b]�� �����ش�
		for (int i = f; i <= b; i++) {
			sum += S[i];
		}

		System.out.println(sum);
	}

}
