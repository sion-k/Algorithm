package baekjoon.p13459;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] MAP;
	static int gy; static int gx;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	// ���� ������ �Ķ� ���� �ʱ� ���¿��� 10�� �ȿ� ���� �� �ִ��� ��ȯ
	static boolean BFS(int[] s) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(s);
		boolean[][][][] BOOKED = new boolean[N][M][N][M];
		BOOKED[s[0]][s[1]][s[2]][s[3]] = true;
		int[][][][] DIST = new int[N][M][N][M];
		DIST[s[0]][s[1]][s[2]][s[3]] = 0;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int ry = here[0]; int rx = here[1];
			int by = here[2]; int bx = here[3];
			for (int dir = 0; dir < 4; dir++) {
				int[] there = tilt(here, dir);
				int nry = there[0]; int nrx = there[1];
				int nby = there[2]; int nbx = there[3];
				if (!(nby == 0 && nbx == 0) && !BOOKED[nry][nrx][nby][nbx]) {
					if (nry == 0 && nrx == 0) {return true;}
					DIST[nry][nrx][nby][nbx] = DIST[ry][rx][by][bx] + 1;
					// (nry, nrx)���� �Ÿ��� 10 �̻��̶�� �� ������ �� �ʿ� ����
					if (DIST[nry][nrx][nby][nbx] < 10) {
						q.offer(new int[] {nry, nrx, nby, nbx});
						BOOKED[nry][nrx][nby][nbx] = true;
					}
				}
			}
		}
		return false;
	}

	// �����¿�[0, 3]���� ��￩�� ������ ������ �� ������ ��ġ�� ��ȯ
	// ������ �⺻������ ��ĥ �� ������ ���ۿ� ���� ��� �Ѵ� (0, 0)
	static int[] tilt(int[] p, int d) {
		boolean moved = true;
		int ry = p[0]; int rx = p[1]; int by = p[2]; int bx = p[3];
		while (moved) {
			moved = false;
			int nry = ry; int nrx = rx;
			int nby = by; int nbx = bx;
			if (!(ry == 0 && rx == 0)) {nry = ry + dy[d]; nrx = rx + dx[d];}
			if (!(by == 0 && bx == 0)) {nby = by + dy[d]; nbx = bx + dx[d];}

			if (MAP[nry][nrx] && !(nry == by && nrx == bx)) {
				ry += dy[d]; rx += dx[d];
				if (ry == gy && rx == gx) {ry = 0; rx = 0;}
				moved = true;
			}
			if (MAP[nby][nbx] && !(nby == ry && nbx == rx)) {
				by += dy[d]; bx += dx[d];
				if (by == gy && bx == gx) {by = 0; bx = 0;}
				moved = true;
			}
		}
		return new int[] {ry, rx, by, bx};
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new boolean[N][M];
		int[] RB = new int[4];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = (row.charAt(j) != '#');
				switch (row.charAt(j)) {
				case 'R':
					RB[0] = i; RB[1] = j;
					break;
				case 'B':
					RB[2] = i; RB[3] = j;
					break;
				case 'O':
					gy = i; gx = j;
					break;
				}
			}
		}
		System.out.println(BFS(RB) ? 1 : 0);
	}

}