import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] S;
	static int[][] R;
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

	static boolean[][] visit;
	
	static void fill(int k) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (visit[i][j]) S[i][j] = k;
	}
	
	static void dfs(int y, int x) {
		visit[y][x] = true;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d]; int nx = x + dx[d];
			if (!inRange(ny, nx) || visit[ny][nx] || S[ny][nx] != S[y][x]) continue;
			dfs(ny, nx);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				S[i][j] = Integer.parseInt(st.nextToken());
		}
		R = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				R[i][j] = Integer.parseInt(st.nextToken());
		}
		boolean used = false;
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (S[i][j] != R[i][j]) {
					if (!used) {
						dfs(i, j);
						fill(R[i][j]);
						used = true;
					}
				}
		boolean flag = true;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (S[i][j] != R[i][j])
					flag = false;
		System.out.println(flag ? "YES" : "NO");
	}

}
