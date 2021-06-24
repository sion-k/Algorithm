import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[D + 1][2];
		dp[1] = new int[] {1, 0}; dp[2] = new int[] {0, 1};
		for (int i = 3; i <= D; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
			dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
		}
		int a = dp[D][0]; int b = dp[D][1];
		int x = 1; int y = (K - a) / b; int mod = (K - a) % b;
		while (mod != 0 || x > y) {
			x++; y = (K - a * x) / b; mod = (K - a * x) % b;
		}
		System.out.println(x); System.out.println(y);
	}

}
