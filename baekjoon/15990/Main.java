import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] cache;

	static final int MOD = 1000000009;

	static int dp(int p, int N) {
		if (N < 0) {return 0;}
		if (N == 0) {return 1;}
		if (cache[p][N] != 0) {return cache[p][N];}
		int sum = 0;
		for (int i = 1; i <= 3; i++) {
			if (i == p) {continue;}
			sum = (sum + dp(i, N - i)) % MOD;
		}
		return cache[p][N] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		cache = new int[4][1000001];
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp(0, N)));
			bw.newLine();
		}
		bw.close();
	}

}
