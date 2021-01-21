package baekjoon.p01799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] MAP; // 비숍을 놓을 수 있는지 여부

	static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < N;}

	// 보드를 대각선으로 45도돌려서 생각한다
	static int btk(int i, boolean[] used) {
		if (i == 2 * N - 1) return 0;
		int max = btk(i + 1, used);
		if (i < N) {
			for (int j = 0; j < i + 1; j++)
				if (MAP[i][j] && !used[j]) {
					used[j] = true;
					max = Math.max(max, 1 + btk(i + 1, used));
					used[j] = false;
				}
		} else {
			for (int j = 0; j < 2 * N - i - 1; j++)
				if (MAP[i][j] && !used[j]) {
					used[j] = true;
					max = Math.max(max, 1 + btk(i + 1, used));
					used[j] = false;
				}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MAP = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				MAP[i][j] = st.nextToken().equals("1");
		}
		boolean[] temp = new boolean[N * N];
		int y = 0; int x = 0; int k = 1; int l = 0;
		for (int i = 0; i < N * N; i++) {
			temp[i] = MAP[y][x];
			y++; x--;
			if (!inRange(y, x)) {
				y = l; x = k;
				if (k + 1 < N) k++;
				else l++;
			}
		}
		MAP = new boolean[2 * N - 1][N]; int t = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < i + 1; j++)
				MAP[i][j] = temp[t++];
		for (int i = N; i < 2 * N - 1; i++)
			for (int j = 0; j < 2 * N - i - 1; j++)
				MAP[i][j] = temp[t++];
		System.out.println(btk(0, new boolean[N]));
	}

}