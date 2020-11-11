package baekjoon.p09328;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static char[][] MAP;

	static final int STATE = (int)(Math.pow(2, 26)) - 1;
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int BFS(int start) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, start});
		boolean[][][] booked = new boolean[N][M][STATE];
		booked[0][0][start] = true;
		int doc = 0;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1]; int s = here[2];
			if (MAP[y][x] == '$') {doc++;}
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (!inRange(ny, nx) || MAP[ny][nx] == '*' || booked[ny][nx][s]) {
					continue;
				}
				if ('a' <= MAP[ny][nx] && MAP[ny][nx] <= 'z') {

				} else if ('A' <= MAP[ny][nx] && MAP[ny][nx] <= 'Z') {

				} else {

				}
			}
		}
		return doc;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()) + 1;
			M = Integer.parseInt(st.nextToken()) + 1;
			MAP = new char[N][M];
			Arrays.fill(MAP[0], '.');
			for (int i = 1; i <= N; i++) {
				Arrays.fill(MAP[i], '.');
				String row = br.readLine();
				for (int j = 1; j <= M; j++) {
					MAP[i][j] = row.charAt(j - 1);
				}
			}
			int state = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(BFS(state)));
			bw.newLine();
		}
		bw.close();
	}

}