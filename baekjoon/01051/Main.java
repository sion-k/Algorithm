import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] S;
	
	static final int[] dy = { 0, 1, 1 };
	static final int[] dx = { 1, 0, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static boolean check(int y, int x, int k) {
		boolean flag = true;
		for (int d = 0; d < 3; d++) {
			int ny = y + k * dy[d]; int nx = x + k * dx[d];
			if (!inRange(ny, nx) || S[ny][nx] != S[y][x]) flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = line.charAt(j) - '0';
		}
		int max = 1;
		for (int y = 0; y < N; y++)
			for (int x = 0; x < M; x++)
				for (int k = 1; k <= 50; k++)
					if (check(y, x, k))
						max = Math.max(max, k + 1);
		System.out.println(max * max);
	}

}
