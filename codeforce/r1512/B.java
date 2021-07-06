import java.io.*;
import java.util.*;

public class B {
	static int N;
	static char[][] S;
	static int[][] C;
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; } 
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static void spread(int y, int x, int d) {
		y += dy[d]; x += dx[d];
		while (inRange(y, x)) {
			C[y][x]++;
			y += dy[d]; x += dx[d];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			S = new char[N][N];
			C = new int[N][N];
			int[][] p = new int[2][2]; int t = 0;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					S[i][j] = line.charAt(j);
					if (S[i][j] == '*') p[t++] = new int[] { i, j };
				}
			}
			C[p[0][0]][p[0][1]] = C[p[1][0]][p[1][1]] = 1;
			for (int d = 0; d < 4; d++) {
				spread(p[0][0], p[0][1], d);
				spread(p[1][0], p[1][1], d);
			}
			if (p[0][0] != p[1][0] && p[0][1] != p[1][1]) {
				for (int i = 0; i < N; i++)
					for (int j = 0; j < N; j++)
						if (C[i][j] == 2) S[i][j] = '*';
			} else if (p[0][0] == p[1][0]) {
				if (inRange(p[0][0] - 1, p[1][0] - 1)) {
					S[p[0][0] - 1][p[0][1]] = S[p[1][0] - 1][p[1][1]] = '*';
				} else {
					S[p[0][0] + 1][p[0][1]] = S[p[1][0] + 1][p[1][1]] = '*';
				}
			} else {
				if (inRange(p[0][1] - 1, p[1][1] - 1)) {
					S[p[0][0]][p[0][1] - 1] = S[p[1][0]][p[1][1] - 1] = '*';
				} else {
					S[p[0][0]][p[0][1] + 1] = S[p[1][0]][p[1][1] + 1] = '*';
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					ans.append(S[i][j]);
				ans.append("\n");
			}
		}
		System.out.print(ans);
	}

}