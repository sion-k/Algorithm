import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] BOARD;
	static int[][] cache;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int dp(int y, int x) {
		if (y == N - 1 && x == M - 1) {return 1;}
		if (cache[y][x] != -1) {return cache[y][x];}
		int sum = 0;
		for (int next = 0; next < 4; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if (inRange(ty, tx) && BOARD[ty][tx] < BOARD[y][x]) {
				sum += dp(ty, tx);
			}
		}
		return cache[y][x] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N =Integer.parseInt(st.nextToken());
		M =Integer.parseInt(st.nextToken());
		cache = new int[N][M]; BOARD = new int[N][M];
		for (int i = 0; i < N; i++) {Arrays.fill(cache[i], -1);}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				BOARD[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dp(0, 0));
	}
}
