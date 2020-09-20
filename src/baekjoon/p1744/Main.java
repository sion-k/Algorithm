package baekjoon.p1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
	private static int[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		S = new int[N];
		for (int i = 0; i < N; i++) {
			S[i] = new Random().nextInt(10001);
			if (new Random().nextBoolean()) {
				S[i] = -1 * S[i];
			}
			if (new Random().nextInt(5) == 0) {
				S[i] = 0;
			}
			// S[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		Arrays.sort(S);
		System.out.println(Arrays.toString(S));

		// [0, f)은 이미 순회한 상태
		int f = 0;
		long sum = 0;
		System.out.println("앞에서 부터 : ");
		while (f + 1 < N && S[f] < 0 && S[f + 1] < 0) {
			System.out.println(S[f] + ", " + S[f + 1] + "묶음");
			sum += (S[f] * S[f + 1]);
			f += 2;
		}
		// 짝 지어지지 않은 음수와 0을 묶어준다
		if (f + 1 < N && S[f] < 0 && S[f + 1] == 0) {
			System.out.println(S[f] + ", " + S[f + 1] + "묶음");
			f += 2;
		}

		// (b, N)은 이미 순회한 상태
		int b = N - 1;
		System.out.println("뒤에서 부터 : ");
		while (f <= b - 1 && S[b - 1] > 0 && S[b] > 0) {
			System.out.println(S[b - 1] + ", " + S[b] + "묶음");
			sum += (S[b - 1] * S[b]);
			b -= 2;
		}

		for (int i = f; i <= b; i++) {
			System.out.println(S[i] +" 더함");
			sum += S[i];
		}
		System.out.println(sum);
	}

}
