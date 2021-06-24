import java.util.*;

public class Main {
	static int[] cache;

	static int dp(int N) {
		if (N <= 2) {return N;}
		if (cache[N] != -1) {return cache[N];}
		return cache[N] = (dp(N - 1) + dp(N - 2)) % 10007;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		cache = new int[N + 1];
		Arrays.fill(cache, -1);
		System.out.println(dp(N));
	}

}
