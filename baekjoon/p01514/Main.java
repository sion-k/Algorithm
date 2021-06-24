package baekjoon.p01514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	// S : 초기상태 G : 목표
	static int[] S; static int[] G;
	static int[][][][][] cache;

	static final int INF = 987654321;

	// here의 값에 diff만큼 돌린후 그 결과를 반환한다 (음수인 경우 감소하게 돌리는 것)
	static int rotate(int here, int diff, int d) {
		return d > 0 ? (here + diff + 10) % 10 : (here - diff + 10) % 10;
	}

	// S[i, )에 대해 S[i], S[i + 1], S[i + 2]의 값이 각각 p, q, r일때
	// 그리고 회전 방향이 증가인지 감소인지 d (0 감소, 1 증가)
	// 자물쇠의 풀기 위한 최소 회전 횟수
	static int dp(int i, int p, int q, int r, int d) {
		if (i == N) return 0;
		// 이미 맞춰져있는 경우 넘어간다
		if (p == G[i])
			return cache[i][p][q][r][d] = Math.min
			(dp(i + 1, q, r, S[i + 3], 0), dp(i + 1, q, r, S[i + 3], 1));
		if (cache[i][p][q][r][d] != -1) return cache[i][p][q][r][d];
		cache[i][p][q][r][d] = INF;
		// S[i]에서 할 수 있는 행동은 디스크의 값을 +- 1, 2, 3변화시키는 것
		// 그리고 1, 2, 3개의 디스크를 같이 돌릴 수 있다
		for (int diff = 1; diff <= 3; diff++) {
			int np = rotate(p, diff, d); int nq = rotate(q, diff, d); int nr = rotate(r, diff, d);
			// 1개의 디스크만 돌리는 경우
			cache[i][p][q][r][d] = Math.min(cache[i][p][q][r][d], 1 + dp(i, np, q, r, d));
			// 2개의 디스크를 같이 돌리는 경우
			if (i <= N - 2)
				cache[i][p][q][r][d] = Math.min(cache[i][p][q][r][d], 1 + dp(i, np, nq, r, d));
			// 3개의 디스크를 같이 돌리는 경우
			if (i <= N - 3)
				cache[i][p][q][r][d] = Math.min(cache[i][p][q][r][d], 1 + dp(i, np, nq, nr, d));
		}
		return cache[i][p][q][r][d];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N + 3]; G = new int[N + 3];
		String line = br.readLine();
		for (int i = 0; i < N; i++)
			S[i] = line.charAt(i) - '0';
		line = br.readLine();
		for (int i = 0; i < N; i++)
			G[i] = line.charAt(i) - '0';
		for (int i = N; i < N + 3; i++)
			S[i] = G[i] = 0;
		cache = new int[N][10][10][10][2];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 10; k++)
					for (int l = 0; l < 10; l++)
						Arrays.fill(cache[i][j][k][l], -1);
		System.out.println(Math.min(dp(0, S[0], S[1], S[2], 0), dp(0, S[0], S[1], S[2], 1)));
	}

}