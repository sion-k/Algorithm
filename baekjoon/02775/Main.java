import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] cache;

	static int dp(int k, int n) {
		if (k == 0) return n;
		if (cache[k][n] != 0) return cache[k][n];
		int sum = 0;
		for (int i = 1; i <= n; i++)
			sum += dp(k - 1, i);
		return cache[k][n] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cache = new int[15][15];
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			ans.append(dp(k, n)).append("\n");
		}
		System.out.print(ans);
	}

}
