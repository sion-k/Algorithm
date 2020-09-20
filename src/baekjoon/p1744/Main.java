package baekjoon.p1744;

import java.io.*;
import java.util.Arrays;

public class Main {
	private static int[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		S = new int[N];
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		Arrays.sort(S);

		// [0, f)은 이미 순회한 상태
		int f = 0;
		long sum = 0;
		while (f + 1 < N && S[f] < 0 && S[f + 1] < 0) {
			sum += (S[f] * S[f + 1]);
			f += 2;
		}
		// 짝 지어지지 않은 음수와 0을 묶어준다
		if (f + 1 < N && S[f] < 0 && S[f + 1] == 0) {
			f += 2;
		}

		// (b, N - 1]은 이미 순회한 상태
		int b = N - 1;
		while (f <= b - 1 && S[b - 1] > 1 && S[b] > 1) {
			sum += (S[b - 1] * S[b]);
			b -= 2;
		}
		
		// 순회하면서 묶지 않은 [f, b]를 더해준다
		for (int i = f; i <= b; i++) {
			sum += S[i];
		}

		System.out.println(sum);
	}

}
