import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][][][] cache;
	
	static final int MOD = 1000000007;
	
	// start번째 도시에서 length길이 이상의 도로를 m개 건설하려고 할 때,
	// [start, start + K]의 상태가 b일때 경우의 수
	static int dp(int start, int length, int m, int b) {
		// 맨 마지막 도시인 경우 더 이상 지을 간선이 없고, b에 나타난 도시들의 연결된 간선들의 개수가 모두 짝수여야 경우를 하나 찾음
		// 더 이상 지을 간선이 없는 경우, b에 나타난 도시들의 연결된 간선들의 개수가 모두 짝수여야 경우를 하나 찾음
		if (start >= N - 1 || m == 0) return m == 0 && b == 0 ? 1 : 0;
		// 간선의 길이가 K를 넘었거나, 범위를 벗어나면 다음 도시에 대해서 시도
		if (length > K || start + length >= N) return (b & 1) == 0 ? dp(start + 1, 1, m, b >> 1) : 0;
		if (cache[start][length][m][b] != -1) return cache[start][length][m][b];
		// length길이의 간선을 짓지 않는 경우
		int sum = dp(start, length + 1, m, b);
		// length길이의 간선을 하나 더 짓는 경우
		sum = (sum + dp(start, length, m - 1, b ^ 1 ^ (1 << length))) % MOD;
		return cache[start][length][m][b] = sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cache = new int[N][K + 1][M + 1][1 << (K + 1)];
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < cache[i].length; j++)
				for (int k = 0; k < cache[i][j].length; k++)
					Arrays.fill(cache[i][j][k], -1);
		System.out.println(dp(0, 1, M, 0));
	}

}
