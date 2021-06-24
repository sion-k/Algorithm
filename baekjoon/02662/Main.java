import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int M; static int[][] S; // i만원을 j기업에 투자할때 얻는 이익
	static int[][] cache;
	// i번째 기업부터 시작해서 k만원이 남아있을 때, 얻을 수 있는 최대 이득
	static int dp(int i, int k) {
		if (i == M) return 0;
		if (cache[i][k] != -1) return cache[i][k];
		int max = dp(i + 1, k); // [0, k]원 투자하는 경우 모두 시도
		for (int j = 1; j <= k; j++) max = Math.max(max, S[j][i] + dp(i + 1, k - j));
		return cache[i][k] = max;
	}
	
	static void reconstruct(int i, int k, int[] ans) {
		if (i == M) return;
		int max = dp(i + 1, k); int best = 0;
		for (int j = 1; j <= k; j++) {
			int cand = S[j][i] + dp(i + 1, k - j);
			if (max < cand) {max = cand; best = j;}
		}
		ans[i] = best;
		reconstruct(i + 1, k - best, ans);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			for (int j = 0; j < M; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		cache = new int[M][N + 1];
		for (int i = 0; i < M; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, N));
		int[] ans = new int[M];
		reconstruct(0, N, ans);
		StringBuilder sb = new StringBuilder();
		for (int a : ans) sb.append(a).append(" ");
		System.out.println(sb.toString().trim());
	}

}
