import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] cache;

	static int dp(int n, int b) {
		if (n == 0) {return 0;}
		if (cache[n][b] != 0) {return cache[n][b];}
		int max = 1 + dp(n - 1, b);
		if (n >= 3) {max = Math.max(max, dp(n - 3, b));}
		if (b != 0) {max = Math.max(max, dp(n - 1, b));}
		return cache[n][b] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		cache = new int[N + 1][N + 1];
		bw.write(String.valueOf(dp(N, 0)));
		bw.close();
	}

}
