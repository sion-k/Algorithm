import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M; static boolean[][] S; // 이미 놓여져 있는지 여부
	static int[][] cache;
	
	// [j, M)의 체스판에 놓을 수 있는 타일의 최대 개수
	// j번째 열에 놓여져 있는지 여부는 b에 비트마스킹으로 나타냄
	static int dp(int j, int b) {
		if (j == M - 1) return 0; // 타일은 오른쪽으로 튀어나와 있으므로 맨 마지막 칸에 설치 불가
		if (cache[j][b] != -1) return cache[j][b];
		return cache[j][b] = btk(j, 0, b, 0);
	}
	
	static int btk(int j, int i, int b, int nb) {
		if (i == N) return dp(j + 1, nb);
		// 현재 칸이나 오른쪽 칸이 이미 놓여져있는 경우 놓을 수 없다.
		if (S[i][j] || S[i][j + 1] || (b & (1 << i)) > 0 || (nb & (1 << i)) > 0)
			return btk(j, i + 1, b, nb);
		int max = btk(j, i + 1, b, nb); // 놓지 않는 경우
		b |= (1 << i); nb |= (1 << i);
		// ㄴ모양 : 현재 칸이 검정 칸이고, 위쪽 타일이 놓을 수 있어야 함
		if ((i + j) % 2 == 0 && i - 1 >= 0 && !S[i - 1][j] && (b & (1 << (i - 1))) == 0)
			max = Math.max(max, 1 + btk(j, i + 1, b, nb));
		// 90도 회전 : 현재 칸이 검정 칸이고, 아래쪽 타일이 놓을 수 있어야 함
		if ((i + j) % 2 == 0 && i + 1 < N && !S[i + 1][j] && (b & (1 << (i + 1))) == 0)
			max = Math.max(max, 1 + btk(j, i + 2, b | (1 << (i + 1)), nb));
		// 90도 회전 : 현재 칸이 흰색 칸이고, 오른쪽 아래 타일이 놓을 수 있어야 함
		if ((i + j) % 2 == 1 && i + 1 < N && !S[i + 1][j + 1])
			max = Math.max(max, 1 + btk(j, i + 1, b, nb | (1 << (i + 1))));
		// 90도 회전 : 현재 칸이 흰색 칸이고, 오른쪽 위 타일이 놓을 수 있어야 함
		if ((i + j) % 2 == 1 && i - 1 >= 0 && !S[i - 1][j + 1] && (nb & (1 << (i - 1))) == 0)
			max = Math.max(max, 1 + btk(j, i + 1, b, nb | (1 << (i - 1))));
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = row.charAt(j) == 'X';
		}
		cache = new int[M][1 << N];
		for (int i = 0; i < M; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}
	
}
