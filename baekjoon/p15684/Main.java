package baekjoon.p15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] S; // N, M 가로선이 있는지 여부

	static int min = 4;

	// i번째 행 j번째 열부터 가로 선을 놓는다
	// 지금까지 l개의 가로선을 추가했을 때,
	// 추가해야하는 최소의 가로선 개수를 갱신
	static void btk(int i, int j, int l) {
		if (l >= min) return; // 3개를 넘게 놓을 수 없다.
		// 맨 아래 맨 오른쪽이면 끝
		if (i == N - 1 && j >= M - 1) {
			if (sat()) min = Math.min(min, l);
			return;
		}
		// 맨 오른쪽 열에는 놓을 수 없다
		if (j >= M - 1) {
			btk(i + 1, 0, l);
			return;
		}
		// 가로선을 놓는 경우
		if (!S[i][j]) {
			S[i][j] = true;
			btk(i, j + 2, l + 1);
			S[i][j] = false;
		}
		// 가로선을 놓지 않는 경우
		btk(i, j + 1, l);
	}

	// i번 세로선의 결과가 i번이 나오는지 확인
	static boolean sat() {
		for (int j = 0; j < M; j++)
			if (dest(0, j) != j) return false;
		return true;
	}

	// j번째 세로 선의 i번 위치에서 내려가면 도달하는 번호 반환
	static int dest(int i, int j) {
		while (i < N) {
			if (S[i][j]) j++;
			else if (j > 0 && S[i][j - 1]) j--;
			i++;
		}
		return j;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		S = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			S[a - 1][b - 1] = true;
		}
		btk(0, 0, 0);
		System.out.println(min == 4 ? -1 : min);
	}

}