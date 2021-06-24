import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] S;
	static int[][][] cache;
	static final int INF = 987654321;
	
	// u에서 v로 이동하는데 소모되는 힘
	static int dist(int u, int v) {
		if (u == 0) return 2;
		if (Math.abs(u - v) == 2) return 4;
		return 3;
	}
	
	static int dp(int l, int r, int i) {
		if (i == S.length) return 0;
		if (cache[l][r][i] != 0) return cache[l][r][i];
		// 이미 어느 한 쪽 발이 S[i]의 위치에 있는 경우 다른 발을 이동시킬 순 없다
		if (l == S[i] || r == S[i]) return cache[l][r][i] = 1 + dp(l, r, i + 1);
		int min = INF;
		min = Math.min(min, dist(l, S[i]) + dp(S[i], r, i + 1)); // 왼발을 이동시키는 경우
		min = Math.min(min, dist(r, S[i]) + dp(l, S[i], i + 1)); // 오른발을 이동시키는 경우
		return cache[l][r][i] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		S = new int[st.countTokens() - 1];
		for (int i = 0; i < S.length; i++) S[i] = Integer.parseInt(st.nextToken());
		cache = new int[5][5][S.length];
		System.out.println(dp(0, 0, 0));
	}

}
