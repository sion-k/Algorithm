import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] BOARD;
	static long[][] cache;

	// 아래 오른쪽 오른쪽 아래
	static final int[] dy = {1, 0, 1};
	static final int[] dx = {0, 1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static long dp(int y, int x) {
		if (y == N - 1 && x == N - 1) {return 1;}
		if (BOARD[y][x] == 0) {return 0;}
		if (cache[y][x] != -1) {return cache[y][x];}
		long sum = 0;
		if (inRange(y, x + BOARD[y][x])) {// 오른쪽 점프
			sum += dp(y, x + BOARD[y][x]);
		}
		if (inRange(y + BOARD[y][x], x )) {// 아래 점프
			sum += dp(y + BOARD[y][x], x);
		}
		return cache[y][x] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N =Integer.parseInt(st.nextToken());
		cache = new long[N][N]; BOARD = new int[N][N];
		for (int i = 0; i < N; i++) {Arrays.fill(cache[i], -1);}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				BOARD[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dp(0, 0));
	}

}
