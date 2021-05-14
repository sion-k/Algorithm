package baekjoon.p01700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		// ��Ƽ�ǿ� ���� ���� ��ǰ��, 0�̸� ����ִ� ���
		int[] M = new int[N];
		int K = Integer.parseInt(st.nextToken());
		// ���� ��ǰ�� ��� ����
		int[] S = new int[K];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {S[i] = Integer.parseInt(st.nextToken());}
		int poll = 0;
		for (int toUse = 0; toUse < K; toUse++) {
			// �̹� ������ �ְų�, �� ���� �ִ��� Ȯ���Ѵ�
			boolean pluged = false; int lastEmpty = -1;
			for (int i = 0; i < N; i++) {
				// �̹� ������ �ִ� ���
				if (M[i] == S[toUse]) {pluged = true;}
				// ���������� ����ִ� ��ġ�� ����
				if (M[i] == 0) {lastEmpty = i;}
			}
			// �̹� ������ ������ �Ѿ��
			if (pluged) {continue;}
			// �ƴ϶�� �� ���� ���� �� �ִٸ� �Ȱ�
			else if (lastEmpty != -1) {M[lastEmpty] = S[toUse];}
			// �� ���� ���ٸ� �̾ƾ� �Ѵ�. ������ �ִ� �͵� �� ���Ǵ°� ���� �ʴ°� �̴´�.
			else {
				// ���ķ� �ѹ��� ������ �ʴ� ���� 100���� �ʱ�ȭ
				int[] firstUse = new int[N];
				Arrays.fill(firstUse, 100);
				// �̹� ������ �ִ� ��� ���� ��ǰ�� ���� Ȯ��
				for (int i = 0; i < N; i++)
					// ���� ��� �������� ������ Ȯ���ؼ�
					for (int j = toUse + 1; j < K; j++)
						// �ٽ� ���Ǵ� ��� �����ϰ� Ż��
						if (S[j] == M[i]) {firstUse[i] = j; break;}
				// ���Ǵ°� ���� ������ ã�´�
				int maxIndex = 0;
				for (int i = 0; i < N; i++)
					if (firstUse[i] > firstUse[maxIndex])
						maxIndex = i;
				// �� ��ġ�� �̰� ���� �Ŵ´�
				M[maxIndex] = S[toUse];
				poll++;
			}
		}
		System.out.println(poll);
	}

}