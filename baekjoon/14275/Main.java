import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][][] cache;
	
	static final int MOD = 1000000007;
	
	// [i, N)크기의 직사각형을 조건을 만족하면서 칠하는 경우의 수
	// i - 1번째 행이 색칠되어있는지 여부 painted
	// i - 1번째 행이 색칠되었다면 인접한 색칠된 칸의 수 adj
	static int dp(int i, int painted, int adj) {
		if (i == N) {
			for (int j = 0; j < M; j++)
				if ((painted & (1 << j)) > 0)
					if ((adj & (1 << j)) > 0) return 0;
			return 1;
		}
		if (cache[i][painted][adj] != -1) return cache[i][painted][adj];
		return cache[i][painted][adj] = btk(i, 0, painted, adj, 0, adj);
	}
	
	static int btk(int i, int j, int p, int a, int np, int na) {
		if (j == M) return dp(i + 1, np, na);
		int sum = 0;
		// 직전 행이 색칠되어있지 않다면 색칠 하거나 안할 수 있다
		if ((p & (1 << j)) == 0) {
			sum = (sum + btk(i, j + 1, p, a, np, na)) % MOD;
			// 색칠하는 경우 j - 1번째가 색칠되었는지 확인
			if (j - 1 >= 0 && (np & (1 << (j - 1))) > 0) {
				na ^= (1 << (j - 1));
				na ^= (1 << j);
			}
			sum = (sum + btk(i, j + 1, p, a, np | (1 << j), na)) % MOD;
		// 색칠되어있고, 인접한 색칠된 칸의 수가 짝수라면 칠할 수 없다
		} else if ((a & (1 << j)) == 0){
			sum = (sum + btk(i, j + 1, p, a, np, na)) % MOD;
		// 색칠되어있고, 인접한 색칠된 칸의 수가 홀수라면 칠해야 한다
		} else {
			// 색칠하는 경우 j - 1번째가 색칠되었는지 확인
			if (j - 1 >= 0 && (np & (1 << (j - 1))) > 0) {
				na ^= (1 << (j - 1));
				na ^= (1 << j);
			}
			sum = (sum + btk(i, j + 1, p, a, np | (1 << j), na)) % MOD;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cache = new int[N][1 << M][1 << M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < (1 << M); j++)
				Arrays.fill(cache[i][j], -1);
		System.out.println(dp(0, 0, 0));
	}
	
}
