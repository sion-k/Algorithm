package baekjoon.p09368;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] S = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				S[i][0] = Integer.parseInt(st.nextToken());
				S[i][1] = Integer.parseInt(st.nextToken());
			}
			double max = 0;
			// �õ� ������ ��� ������ Ȯ���Ѵ�
			for (int pick = 1; pick < (1 << N); pick++) {
				if (Integer.bitCount(pick) < C) continue;
				int cost = 0;
				for (int i = 0; i < N; i++)
					if ((pick & (1 << i)) > 0) cost += S[i][0];
				if (cost > M) continue;
				double sum = 0;
				// pick�� �κ����� ��� ��ȸ, ��Ȯ�ϰ� C�� �����ϴ� ��� Ȯ���� ���Ѵ�
				for (int subset = pick; subset != 0; subset = ((subset - 1) & pick)) {
					if (Integer.bitCount(subset) != C) continue;
					double temp = 1;
					for (int i = 0; i < N; i++) {
						if ((pick & (1 << i)) == 0) continue;
						if ((subset & (1 << i)) > 0) temp *= (S[i][1] / 100.0);
						else temp *= ((100 - S[i][1]) / 100.0);
					}
					sum += temp;
				}
				System.out.println(String.format("%s %f", Integer.toBinaryString(pick), sum));
				max = Math.max(max, sum);
			}
			ans.append(max).append("\n");
		}
		System.out.print(ans);
	}
	
}