package baekjoon.p02305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, K;
	static int[][][] cache;
	
	// i - 1��° �¼��� �ɴ� ����� t��° Ƽ���� �� ����̰� (0�̸� ����δ� ���)
	// ���������� f��° Ƽ���� �� ����� �ɰ�(0�̸� ����δ� ���)
	// [i, ...]���� �¼��� ä��� ����� ��
	static int dp(int i, int t, int f) {
		if (i > N) return 1;
		if (cache[i][t][f] != -1) return cache[i][t][f];
		if (i == K) return dp(i + 1, t, f); // �������� ��� �̹� ��ġ�����Ƿ� �Ѿ
		int sum = 0;
		if (f == 0 || i != f) { // �������� ����ų� ������ �� ��� �ڸ��� �ƴ϶�� ���� Ƽ���� �� ����� �ɾƾ� �Ѵ�
			sum += dp(i + 1, i, f);
		} else { // �������� ������ �ɾҴٸ�, �׸��� ���� ��ġ�� �� ����� �ڸ���� ������Ѵ�
			sum += dp(i + 1, 0, f);
		}
		// �ٷ� ���� ĭ�� �������� �ƴϰ�, �� ����� �ٲ� �� �ִ� ��� �ٲ㺻��
		if (i - 1 >= 1 && i - 1 != K && (i == t + 1 || t == 0)) 
			sum += dp(i + 1, t, f);
		return cache[i][t][f] = sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		cache = new int[N + 1][N + 2][N + 1];
		for (int i = 0; i < N + 1; i++)
			for (int j = 0; j < N + 2; j++)
				Arrays.fill(cache[i][j], -1);
		int sum = 0;
		for (int f = 0; f <= N; f++)
			if (f != K) sum += dp(1, N + 1, f);
		System.out.println(sum);
	}
	
}