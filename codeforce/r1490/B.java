package codeforce.c1490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			int min = 987654321;
			int[] C = new int[3];
			for (int i = 0; i < N; i++) C[S[i] % 3]++;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == j) continue;
					for (int k = 0; k < 3; k++) {
						if (i == k || j == k) continue;
						int[] temp = C.clone();
						int cnt = 0;
						// i j k순으로 이동
						if (C[i] > N / 3) {
							C[(i + 1) % 3] += (C[i] - N / 3);
							cnt += (C[i] - N / 3);
							C[i] = N / 3;
						}
						if (C[j] > N / 3) {
							C[(j + 1) % 3] += (C[j] - N / 3);
							cnt += (C[j] - N / 3);
							C[j] = N / 3;
						}
						if (C[k] > N / 3) {
							C[(k + 1) % 3] += (C[k] - N / 3);
							cnt += (C[k] - N / 3);
							C[k] = N / 3;
						}
						if (C[i] == C[j] && C[i] == C[k]) min = Math.min(min, cnt);
						C = temp;
					}
				}
			}
			ans.append(min).append("\n");
		}
		System.out.print(ans.toString().trim());
	}

}