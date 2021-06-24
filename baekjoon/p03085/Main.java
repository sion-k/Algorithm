package baekjoon.p03085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N; static char[][] S;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < N;}

	// 현재 S에서 먹을 수 있는 사탕의 최대 개수 반환
	static int max() {
		int ret = 1;
		for (int i = 0; i < N; i++) {
			int temp = 1;
			for (int j = 0; j < N; j++) {
				if (j > 0 && S[i][j] == S[i][j - 1]) temp++;
				else {
					ret = Math.max(ret, temp);
					temp = 1;
				}
			}
			ret = Math.max(ret, temp);
		}
		for (int j = 0; j < N; j++) {
			int temp = 1;
			for (int i = 0; i < N; i++) {
				if (i > 0 && S[i][j] == S[i - 1][j]) temp++;
				else {
					ret = Math.max(ret, temp);
					temp = 1;
				}
			}
			ret = Math.max(ret, temp);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new char[N][N];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++)
				S[i][j] = row.charAt(j);
		}
		int ret = 0;
		// 서로 인접한 사탕 두개를 고른다
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d]; int nx = x + dx[d];
					if (inRange(ny, nx) && S[y][x] != S[ny][nx]) { // 서로 다르다면 바꿈
						char temp = S[y][x]; S[y][x] = S[ny][nx]; S[ny][nx] = temp;
						ret = Math.max(ret, max());
						temp = S[y][x]; S[y][x] = S[ny][nx]; S[ny][nx] = temp;
					}
				}
			}
		}
		System.out.println(ret);
	}

}
