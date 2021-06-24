import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 987654321;
	static int[] S;
	static int[][] cache;

	// i번째 까지의 연속합중 i번째 연속합을 선택하거나 선택하지 않는 합 중 최대
	static int dp(int i, int c) {
		if (i < 0) {return 0;}
		if (cache[c][i] != INF) {return cache[c][i];}
		if (c == 0) {
			return cache[c][i] = Math.max(dp(i - 1, 0), dp(i - 1, 1));
		} else {
			return cache[c][i] = S[i] + Math.max(0, dp(i - 1, 1));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		S = new int[N]; cache = new int[2][N];
		Arrays.fill(cache[0], INF); Arrays.fill(cache[1], INF);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		cache[0][0] = S[0];
		System.out.println(Math.max(dp(N - 1, 0), dp(N - 1, 1)));
	}

}
