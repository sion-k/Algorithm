package baekjoon.p09613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[] S = new int[N];
			for (int i = 0; i < N; i++)
				S[i] = Integer.parseInt(st.nextToken());
			long sum = 0;
			for (int i = 0; i < N - 1; i++)
				for (int j = i + 1; j < N; j++)
					sum += gcd(S[i], S[j]);
			ans.append(sum).append("\n");
		}
		System.out.print(ans);
	}

}