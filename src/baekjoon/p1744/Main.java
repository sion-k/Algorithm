package baekjoon.p1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
	private static int[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		S = new int[N];
		for (int i = 0; i < N; i++) {
			S[i] = new Random().nextInt(10001);
			if (new Random().nextBoolean()) {
				S[i] = -1 * S[i];
			}
			if (new Random().nextInt(5) == 0) {
				S[i] = 0;
			}
			// S[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		Arrays.sort(S);
		System.out.println(Arrays.toString(S));

		// [0, f)�� �̹� ��ȸ�� ����
		int f = 0;
		long sum = 0;
		System.out.println("�տ��� ���� : ");
		while (f + 1 < N && S[f] < 0 && S[f + 1] < 0) {
			System.out.println(S[f] + ", " + S[f + 1] + "����");
			sum += (S[f] * S[f + 1]);
			f += 2;
		}
		// ¦ �������� ���� ������ 0�� �����ش�
		if (f + 1 < N && S[f] < 0 && S[f + 1] == 0) {
			System.out.println(S[f] + ", " + S[f + 1] + "����");
			f += 2;
		}

		// (b, N)�� �̹� ��ȸ�� ����
		int b = N - 1;
		System.out.println("�ڿ��� ���� : ");
		while (f <= b - 1 && S[b - 1] > 0 && S[b] > 0) {
			System.out.println(S[b - 1] + ", " + S[b] + "����");
			sum += (S[b - 1] * S[b]);
			b -= 2;
		}

		for (int i = f; i <= b; i++) {
			System.out.println(S[i] +" ����");
			sum += S[i];
		}
		System.out.println(sum);
	}

}
