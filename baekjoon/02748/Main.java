import java.util.*;

public class Main {
	private static long[] cache;

	private static long p(int n) {
		if (n < 2)
			return n;
		if (cache[n] != 0) {
			return cache[n];
		}
		return cache[n] = p(n - 1) + p(n - 2);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		cache = new long[n + 1];
		cache[1] = 1;
		System.out.println(p(n));
		sc.close();
	}

}
