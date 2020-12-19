package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] S = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		// ���� ������ ī�� 3���� ���� ������ [3, 300], �ش� ���� �����ϴ��� ���� ����
		boolean[] set = new boolean[301];
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++)
					set[S[i] + S[j] + S[k]] = true;
		// K��° ���� �������� ������ -1�� ��ȯ
		int ret = -1;
		// [300, 3]������ ��ȸ�ϸ鼭 K��° ���� ã�´�
		for (int i = 300; i >= 3; i--) {
			// �ش� ���� �����ϴ� ���
			if (set[i]) K--;
			// K�� 0�̶�� ���� i�� ���� K��°�� ����
			if (K == 0) {ret = i; break;}
		}
		System.out.println(ret);
	}

}