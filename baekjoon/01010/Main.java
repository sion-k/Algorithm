import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] cache;

	static int dp(int lastPick, int toPick) {
		if (toPick == 0) {return 1;}
		if (cache[lastPick + 1][toPick] != -1) {
			return cache[lastPick + 1][toPick];
		}
		int sum = 0;
		for (int pick = lastPick + 1; pick < M; pick++) {
			sum += dp(pick, toPick - 1);
		}
		return cache[lastPick + 1][toPick] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cache = new int[M + 1 + 1][N + 1];
			for (int i = 0; i < M + 1; i++) {
				Arrays.fill(cache[i], -1);
			}
			bw.write(String.valueOf(dp(-1, N)));
			bw.newLine();
		}
		bw.close();
	}

}
