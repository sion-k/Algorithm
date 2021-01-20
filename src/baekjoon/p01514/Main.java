package baekjoon.p01514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	// S : 초기상태 G : 목표
	static int[] S; static int[] G;
	static int[][][][] cache;

	static final int INF = 987654321;

	// here의 값에 diff만큼 돌린후 그 결과를 반환한다 (음수인 경우 감소하게 돌리는 것)
	static int rotate(int here, int diff) {return (here + diff + 10) % 10;}

	// S[i, )에 대해 S[i], S[i + 1], S[i + 2]의 값이 각각 p, q, r일때
	// 자물쇠의 풀기 위한 최소 회전 횟수
	static int dp(int i, int p, int q, int r) {
		if (i == N) return 0;
		// 이미 맞춰져있는 경우 넘어간다
		if (p == G[i]) return cache[i][p][q][r] = dp(i + 1, q, r, S[i + 3]);
		if (cache[i][p][q][r] != -1) return cache[i][p][q][r];
		cache[i][p][q][r] = INF;
		// S[i]에서 할 수 있는 행동은 디스크의 값을 +- 1, 2, 3변화시키는 것
		// 그리고 1, 2, 3개의 디스크를 같이 돌릴 수 있다
		for (int diff = -3; diff <= 3; diff++) {
			if (diff == 0) continue;
			int np = rotate(p, diff); int nq = rotate(q, diff); int nr = rotate(r, diff);
			// 1개의 디스크만 돌리는 경우
			cache[i][p][q][r] = Math.min(cache[i][p][q][r], 1 + dp(i, np, q, r));
			// 2개의 디스크를 같이 돌리는 경우
			if (i <= N - 2)
				cache[i][p][q][r] = Math.min(cache[i][p][q][r], 1 + dp(i, np, nq, r));
			// 3개의 디스크를 같이 돌리는 경우
			if (i <= N - 3)
				cache[i][p][q][r] = Math.min(cache[i][p][q][r], 1 + dp(i, np, nq, nr));
		}
		return cache[i][p][q][r];
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
		cache = new int[N][10][10][10];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 10; k++)
					Arrays.fill(cache[i][j][k], -1);
		System.out.println(dp(0, S[0], S[1], S[2]));
	}

}