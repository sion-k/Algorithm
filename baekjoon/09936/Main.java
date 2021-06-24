import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] S;
	static int[][][] cache;

	static final int NULL = -2000000000;
	
	// i번째 행부터 체스판을 k개의 도미노로 채워나간다
	// i번째 행에 이미 채워져있는 칸의 여부는 3비트의 f로 나타낸다
	static int dp(int i, int k, int f) {
		if (i == N) return k == 0 ? 0 : NULL;
		if (cache[i][k][f] != NULL) return cache[i][k][f];
		return cache[i][k][f] = btk(i, 0, k, f, 0);
	}
	
	// i번째 행 j번째 열부터 k개의 도미노를 놓을 때
	// i번째 행의 정보가 f고 i+1번째 행의 정보가 nf가될 때
	// dp(i + 1, ...)을 호출하여 얻을 수 있는 최대값
	static int btk(int i, int j, int k, int f, int nf) {
		if (j == 3) return dp(i + 1, k, nf); // 3칸을 모두 시도한경우
		if (k == 0) return 0; // 도미노가 더 이상 없으면 덮을 수 없다
		if ((f & (1 << j)) > 0) return btk(i, j + 1, k, f, nf); // 놓을 수 없는 경우 다음 칸으로 재귀 호출
		// 안놓는 경우
		int max = btk(i, j + 1, k, f, nf);
		// 가능하다면 세로로 놓는 경우
		if (i + 1 < N) max = Math.max(max, S[i][j] + S[i + 1][j] + btk(i, j + 1, k - 1, f, nf | (1 << j)));
		// 가능하다면 가로로 놓는 경우
		if (j + 1 < 3 && (f & (1 << (j + 1))) == 0) max = Math.max(max, S[i][j] + S[i][j + 1] + btk(i, j + 2, k - 1, f, nf));
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		S = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		cache = new int[N][K + 1][8];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < K + 1; j++)
				Arrays.fill(cache[i][j], NULL);
		System.out.println(dp(0, K, 0));
	}

}
