package codeforce.r693;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class C {
	static int N; static int[] S;
	static int[] cache;

	// i번째 위치에서 시작해서 얻을 수 있는 점수의 최대 값
	static int dp(int i) {
		if (i >= N) return 0;
		if (cache[i] != 0) return cache[i];
		return cache[i] = S[i] + dp(i + S[i]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			S = new int[N];
			for (int i = 0; i < N; i++)
				S[i] = Integer.parseInt(st.nextToken());
			cache = new int[N];
			int max = 1;
			for (int i = 0; i < N; i++)
				max = Math.max(max, dp(i));
			bw.write(String.valueOf(max));
			bw.newLine();
		}
		bw.close();
	}

}