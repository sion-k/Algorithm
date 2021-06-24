import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] prime;

	// -1미계산 0 소수가 아님 1 소수
	static void sieve() {
		for (int i = 2; i <= N; i++) {
			if(prime[i] == -1) {
				prime[i] = 1;
				for (int k = i * 2; k <= N; k += i) {
					prime[k] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		prime = new int[N + 1]; prime[1] = 0;
		Arrays.fill(prime, -1);
		sieve();
		int sum = 0;
		int min = 0;
		for (int i = M; i <= N; i++) {
			if (prime[i] == 1) {
				sum += i;
				if (min == 0) {min = i;}
				else {min = Math.min(min, i);}
			}
		}
		if (sum != 0) {
			System.out.println(sum);
			System.out.println(min);
		} else {
			System.out.println(-1);
		}
	}

}
