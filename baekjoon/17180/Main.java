import java.util.*;

public class Main {
	private static String A;
	private static String B;
	private static int[][] cache;

	private static int min(int a, int b, int c) {
		int min = a;
		if (b < min) {
			min = b;
		}
		if (c < min) {
			min = c;
		}
		return min;
	}

	private static int dis(int i, int j) {
		int ret = cache[i][j];
		if (ret != -1) {
			return ret;
		}

		if (A.charAt(i) == B.charAt(j)) {
			return cache[i][j] = min(dis(i - 1, j - 1), dis(i - 1, j), dis(i, j - 1));
		} else {
			return cache[i][j] = (min(dis(i - 1, j - 1), dis(i - 1, j), dis(i, j - 1)))
					+ Math.abs(A.charAt(i) - B.charAt(j));
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] NandM = sc.nextLine().split(" ");
		int N = Integer.parseInt(NandM[0]);
		int M = Integer.parseInt(NandM[1]);

		A = sc.nextLine();
		B = sc.nextLine();
		sc.close();

		cache = new int[N][M];
		for (int i = 0; i < cache.length; i++) {
			Arrays.fill(cache[i], -1);
		}

		cache[0][0] = Math.abs(A.charAt(0) - B.charAt(0));
		for (int i = 1; i < N; i++) {
			cache[i][0] = cache[i - 1][0] + Math.abs(A.charAt(i) - B.charAt(0));
		}
		for (int j = 1; j < M; j++) {
			cache[0][j] = cache[0][j - 1] + Math.abs(B.charAt(j) - A.charAt(0));
		}

		System.out.println(dis(N - 1, M - 1));
	}
}
