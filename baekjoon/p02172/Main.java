package baekjoon.p02172;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] S;
	static int[][][][][] cache;
	
	static final int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static final int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }
	
	// (u1, u2)에서 시작해서 (v1, v2)에서 끝나는 길이 l의 팰린드롬 경로의 개수
	static int dp(int u1, int u2, int v1, int v2, int l) {
		if (l == 2) {
			for (int d = 0; d < 8; d++) {
				int ny = u1 + dy[d]; int nx = u2 + dx[d];
				if (inRange(ny, nx) && ny == v1 && nx == v2) return 1;
			}
			return 0;
		}
		if (l == 1) return u1 == v1 && u2 == v2 ? 1 : 0;
		if (cache[u1][u2][v1][v2][l] != -1) return cache[u1][u2][v1][v2][l];
		int sum = 0;
		for (int d1 = 0; d1 < 8; d1++) {
			for (int d2 = 0; d2 < 8; d2++) {
				int nu1 = u1 + dy[d1]; int nu2 = u2 + dx[d1];
				int nv1 = v1 + dy[d2]; int nv2 = v2 + dx[d2];
				if (!inRange(nu1, nu2) || !inRange(nv1, nv2) || S[nu1][nu2] != S[nv1][nv2]) continue;
				sum += dp(nu1, nu2, nv1, nv2, l - 2);
			}
		}
		return cache[u1][u2][v1][v2][l] = sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); S = new int[N][N];
		int L = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		cache = new int[N][N][N][N][L + 1];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				for (int k = 0; k < N; k++)
					for (int l = 0; l < N; l++)
						Arrays.fill(cache[i][j][k][l], -1);
		int sum = 0;
		for (int u = 0; u < N * N; u++) {
			int u1 = u / N; int u2 = u % N;
			for (int v = 0; v < N * N; v++) {
				int v1 = v / N; int v2 = v % N;
				if (S[u1][u2] == S[v1][v2]) sum += dp(u1, u2, v1, v2, L);
			}
		}
		System.out.println(sum);
	}
	
}
