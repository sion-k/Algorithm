import java.io.*;
import java.util.*;

public class C {
	static int N, M, K;
	static int[][] S;

	static final int[] dy = { 1, 1 };
	static final int[] dx = { -1, 1 };

	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

	static void fill(int y, int x) {
		boolean flag = false;
		S[y][x]++;
		for (int i = 1; i <= N; i++) {
			int ny1 = y + i * dy[0];
			int nx1 = x + i * dx[0];
			int ny2 = y + i * dy[1];
			int nx2 = x + i * dx[1];
			if (inRange(ny1, nx1) && inRange(ny2, nx2) && S[ny1][nx1] >= 1 && S[ny2][nx2] >= 1) {
				S[ny1][nx1]++;
				S[ny2][nx2]++;
			} else {
				break;
			}
			if (i == K) flag = true;
		}
		if (!flag) {
			S[y][x]--;
			for (int i = 1; i <= N; i++) {
				int ny1 = y + i * dy[0];
				int nx1 = x + i * dx[0];
				int ny2 = y + i * dy[1];
				int nx2 = x + i * dx[1];
				if (inRange(ny1, nx1) && inRange(ny2, nx2) && S[ny1][nx1] >= 1 && S[ny2][nx2] >= 1) {
					S[ny1][nx1]--;
					S[ny2][nx2]--;
				} else {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			S = new int[N][M];
			for (int i = N - 1; i >= 0; i--) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) S[i][j] = (line.charAt(j) == '*' ? 1 : 0);
			}
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (S[i][j] >= 1)
						fill(i, j);
			boolean flag = true;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
						if (S[i][j] == 1)
							flag = false;
			bw.append(flag ? "YES" : "NO");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}