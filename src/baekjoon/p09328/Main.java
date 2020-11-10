package baekjoon.p09328;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static char[][] MAP;
	static int DOC;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

//	static int BFS(int state) {
//		Queue<int[]> q = new LinkedList<>();
//		q.offer(new int[] {0, 0, state});
//		boolean[][][] booked = new boolean[N][M][26];
//	}

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
			DOC = 0;
			//BFS();
			bw.write(String.valueOf(DOC));
			bw.newLine();
		}
		bw.close();
	}

}