package baekjoon.p01799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] MAP; // 비숍을 놓을 수 있는지 여부

	// m은 i번째 행에 j열에 비숍을 놓을 수 있는지 없는지에 대한 정보를 제공
	// 0이면 놓을 수 있고 그 이상이면 놓을 수 없음
	// 1이면 왼쪽으로 가는 것 2면 오른쪽 3이면 양쪽으로
	// 현재 상태에서 다음 행으로 넘어갔을 때의 m을 반환
	static int[] update(int[] m) {
		int[] ret = new int[N];
		for (int j = 0; j < N; j++) {
			if (m[j] == 1 && j - 1 >= 0) {
				ret[j - 1] += 1;
			} else if (m[j] == 2 && j + 1 < N) {
				ret[j + 1] += 2;
			} else if (m[j] == 3) {
				if (j - 1 >= 0) ret[j - 1] += 1;
				if (j + 1 < N) ret[j + 1] += 2;
			}
		}
		return ret;
	}

	// MAP[i][j]부터 경우의 수, i번째 j행에 놓을 수 있는지 여부 -> m[j]
	static int btk(int i, int j, int[] m) {
		if (i == N) return 0; // 끝에 도달한 경우 경우의 수를 하나 찾음
		if (j == N) return btk(i + 1, 0, update(m));
		int max = btk(i, j + 1, m); // 놓지 않는 경우
		if (MAP[i][j] && m[j] == 0) { // 놓을 수 있는 경우
			m[j] = 3;
			max = Math.max(max, 1 + btk(i, j + 1, m));
			m[j] = 0;
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
		System.out.println(btk(0, 0, new int[N]));
	}

}