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
	// used는 2 * N - 1크기의 놓을 수 있는지 여부를 저장한다
	// 비숍은 검은색 칸과 흰 칸중 한쪽에만 존재할 수 있으므로 경루를 나누었다.
	static int btk(int i, boolean[] used) {
		if (i >= 2 * N - 1) return 0;
		int max = 0;
		if (i < N) {
			int y = 0; int x = i; int j = N - i - 1;
			// i번째 대각선의 모든 경우를 시도
			while (inRange(y, x)) {
				if (MAP[y][x] && !used[j]) {
					used[j] = true;
					max = Math.max(max, 1 + btk(i + 2, used));
					used[j] = false;
				}
				y++; x--; j += 2;
			}
		} else {
			int y = i - N + 1; int x = N - 1; int j = i - N + 1;
			// i번째 대각선의 모든 경우를 시도
			while (inRange(y, x)) {
				if (MAP[y][x] && !used[j]) {
					used[j] = true;
					max = Math.max(max, 1 + btk(i + 2, used));
					used[j] = false;
				}
				y++; x--; j += 2;
			}
		}
		return Math.max(max, btk(i + 2, used));
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
		System.out.println(btk(0, new boolean[2 * N - 1]) + btk(1, new boolean[2 * N - 1]));
	}

}