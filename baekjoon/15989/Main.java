import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] cache;

	static int dp(int n, int k) {
		if (n == 0) return 1;
		if (cache[n][k] != 0) return cache[n][k];
		int sum = 0;
		for (int i = k; i <= 3; i++)
			if (n - i >= 0)
				sum += dp(n - i, i);
		return cache[n][k] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		cache = new int[10001][4];
		StringBuilder ans = new StringBuilder();
		for (int tc = 0; tc < T; tc++)
			ans.append(dp(Integer.parseInt(br.readLine()), 1)).append("\n");
		System.out.print(ans);
	}

}
