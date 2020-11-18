package baekjoon.p20166;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static char[][] MAP;
	static String W;
	static int[][][] cache;

	static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

	// (y, x)에서 m번째 이동 방법(왼쪽 위 순서)로 도착하는 위치 반환
	static int[] move(int y, int x, int m) {
		int ny = y + dy[m]; int nx = x + dx[m];
		if (ny > N) {ny = 1;} if (ny == 0) {ny = N;}
		if (nx > M) {nx = 1;} if (nx == 0) {nx = M;}
		return new int[] {ny, nx};
	}

	// y x에서 시작해서 s를 만들어 낼 수 있는 경우의 수
	static int dp(int y, int x, int s) {
		if (MAP[y][x] != W.charAt(s)) {return 0;}
		if (s == W.length() - 1) {return 1;}
		if (cache[y][x][s] != -1) {return cache[y][x][s];}
		int sum = 0;
		for (int m = 0; m < 8; m++) {
			int[] next = move(y, x, m);
			int ny = next[0]; int nx = next[1];
			sum += dp(ny, nx, s + 1);
		}
		return cache[y][x][s] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new char[N + 1][M + 1];
		int K = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			String row = br.readLine();
			for (int j = 1; j <= M; j++) {
				MAP[i][j] = row.charAt(j - 1);
			}
		}

		for (int s = 0; s < K; s++) {
			W = br.readLine();
			cache = new int[N + 1][M + 1][W.length()];
			for (int i = 1; i <= N ; i++) {
				for (int j = 1; j <= M; j++) {
					Arrays.fill(cache[i][j], -1);
				}
			}
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					sum += dp(i, j, 0);
				}
			}
			bw.write(String.valueOf(sum));
			bw.newLine();
		}
		bw.close();
	}

}