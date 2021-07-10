package codeforce.r661;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
	static int N; static int S;
	static int[] W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			N = Integer.parseInt(br.readLine());
			W = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				W[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(W);
			int max = 0;
			for (S = 2; S <= 2 * N; S++) {
				boolean[] taken = new boolean[N];
				int cnt = 0;
				for (int i = 0; i < N; i++) {
					if (taken[i]) {continue;}
					for (int j = i + 1; j < N; j++) {
						int sum = W[i] + W[j];
						if (sum < S) {continue;}
						if (sum > S) {break;}
						if (!taken[i] && !taken[j]) {
							cnt++; taken[i] = taken[j] = true;
						}
					}
				}
				max = Math.max(max, cnt);
			}
			bw.write(String.valueOf(max));
			bw.newLine();
		}
		bw.close();
	}

}