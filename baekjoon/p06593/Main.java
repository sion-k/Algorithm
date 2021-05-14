package baekjoon.p06593;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static int L;
	static char[][][] POS;// (y, x, z)

	static int sy; static int sx; static int sz;
	static int gy; static int gx; static int gz;

	// 동서남북상하
	static final int[] dy = { 0, 0, 1, -1, 0, 0 };
	static final int[] dx = { 1, -1, 0, 0, 0, 0 };
	static final int[] dz = { 0, 0, 0, 0, 1, -1 };

	static boolean inRange(int y, int x, int z) {
		return 0 <= y && y < R && 0 <= x && x < C && 0 <= z && z < L;
	}

	// 시작 지점으로부터 끝까지의 최단 거리 반환
	static int BFS() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sy, sx, sz});
		boolean[][][] BOOKED = new boolean[R][C][L];
		BOOKED[sy][sx][sz] = true;
		int[][][] DIST = new int[R][C][L];
		DIST[sy][sx][sz] = 0;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1]; int z = here[2];

			for (int next = 0; next < 6; next++) {
				int ty = y + dy[next]; int tx = x + dx[next]; int tz = z + dz[next];
				if (inRange(ty, tx, tz) && POS[ty][tx][tz] != '#' && !BOOKED[ty][tx][tz]) {
					q.offer(new int[] {ty, tx, tz});
					BOOKED[ty][tx][tz] = true;
					DIST[ty][tx][tz] = DIST[y][x][z] + 1;
					if (ty == gy && tx == gx && tz == gz) {return DIST[ty][tx][tz];}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = "";
		while (!(line = br.readLine()).equals("0 0 0")) {
			StringTokenizer st = new StringTokenizer(line, " ");
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			POS = new char[R][C][L];
			for (int k = 0; k < L; k++) {
				for (int i = 0; i < R; i++) {
					String row = br.readLine();
					for (int j = 0; j < C; j++) {
						POS[i][j][k] = row.charAt(j);
						if (POS[i][j][k] == 'S') {sy = i; sx = j; sz = k;}
						if (POS[i][j][k] == 'E') {gy = i; gx = j; gz = k;}
					}
				}
				br.readLine();
			}
			int ret = BFS();
			if (ret != -1) {bw.write("Escaped in " + BFS() + " minute(s).");
			} else {bw.write("Trapped!");}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}