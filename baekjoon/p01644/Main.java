package baekjoon.p01644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] isPrime = new boolean[N + 2];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		ArrayList<Integer> S = new ArrayList<>();
		for (int i = 2; i <= N; i++)
			if (isPrime[i]) {
				S.add(i);
				for (int j = 2 * i; j <= N; j += i) isPrime[j] = false;
			}
		int ret = 0;
		int head = 0; int tail = 0; long rangeSum = 0;
		while (true) {
			if (rangeSum >= N) rangeSum -= S.get(head++);
			else if (tail == S.size()) break;
			else rangeSum += S.get(tail++);
			if (rangeSum == N) ret++;
		}
		System.out.println(ret);
	}

}