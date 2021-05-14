package baekjoon.p15651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {
	static int N; static int M;
	static int[] S;
	static StringBuilder A = new StringBuilder();

	static void BFC(int picked) {
		if (picked == M) {
			for (int i = 0; i < S.length; i++) {
				A.append(S[i]).append(" ");
			}
			A.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			S[picked] = i;
			BFC(picked + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[M];
		BFC(0);
		System.out.println(A);
	}

}