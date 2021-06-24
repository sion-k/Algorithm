import java.util.Scanner;

public class Main {
	private static int[] cache;
	
	/*
	 * StackOverFlow때문에 써먹을 수가 없네 반복적으로 처리하자
	private static int dp(int N) {
		if (N == 1) {
			return 0;
		}
		if (cache[N] != 0) {
			return cache[N];
		}
		int min = dp(N - 1) + 1;
		if (N % 3 == 0) {
			min = Math.min(min, dp(N / 3) + 1);
		}
		if (N % 2 == 0) {
			min = Math.min(min, dp(N / 2) + 1);
		}
		return cache[N] = min;

	}
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		cache = new int[N + 1];
		for (int i = 2; i < N + 1; i++) {
			int min = cache[i - 1] + 1;
			if (i % 3 == 0) {
				min = Math.min(min, cache[i / 3] + 1);
			}
			if (i % 2 == 0) {
				min = Math.min(min, cache[i / 2] + 1);
			}
			cache[i] = min;
		}
		System.out.println(cache[N]);
	}

}
