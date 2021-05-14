package baekjoon.p15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] MAP;
	static List<CCTV> cctv;

	static final int[] dy = {1, -1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// map�� �簢���� ũ�� ��ȯ
	static int area() {
		int cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (MAP[i][j] == 0)
					cnt++;
		return cnt;
	}

	static int btk(int here) {
		if (here == cctv.size()) return area();
		int min = 987654321;
		CCTV c = cctv.get(here);
		// here��° cctv�� ������ ���
		for (int r = 0; r < 4; r++) {
			c.rotate();
			detect(c, 1);
			min = Math.min(min, btk(here + 1));
			detect(c, -1);
		}
		return min;
	}

	// c�� �ٶ󺸴� ������ �����ϰ� �Ѵ� (a�� -1�̸� ���� ����)
	static void detect(CCTV c, int a) {
		int y = c.y; int x = c.x; int d = c.dir;
		while (inRange(y, x) && MAP[y][x] != 6) {
			MAP[y][x] += (10 * a);
			y += dy[d]; x += dx[d];
		}
		// type�� 2�ų� 5�� �ڿ��� ����
		if (c.type == 2 || c.type == 5) {
			y = c.y; x = c.x; d = CCTV.back[c.dir];
			while (inRange(y, x) && MAP[y][x] != 6) {
				MAP[y][x] += (10 * a);
				y += dy[d]; x += dx[d];
			}
		}
		// type�� 3 �̻��̸� �����ʵ� ����
		if (c.type >= 3) {
			y = c.y; x = c.x; d = CCTV.right[c.dir];
			while (inRange(y, x) && MAP[y][x] != 6) {
				MAP[y][x] += (10 * a);
				y += dy[d]; x += dx[d];
			}
			if (c.type >= 4) {
				y = c.y; x = c.x; d = CCTV.left[c.dir];
				while (inRange(y, x) && MAP[y][x] != 6) {
					MAP[y][x] += (10 * a);
					y += dy[d]; x += dx[d];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		cctv = new ArrayList<CCTV>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				if (1 <= MAP[i][j] && MAP[i][j] <= 5)
					cctv.add(new CCTV(i, j, MAP[i][j]));
			}
		}
		System.out.println(btk(0));
	}

}

class CCTV {
	int y; int x; int type;
	int dir; // [0, 4) : �����¿�

	public CCTV(int y, int x, int t) {
		this.y = y; this.x = x;
		type = t; dir = 0;
	}

	// �����¿� ���� ����
	static final int[] left  = {2, 3, 1, 0};
	static final int[] right = {3, 2, 0, 1};
	static final int[] back  = {1, 0, 3, 2};

	// �ð�������� ������
	public void rotate() {dir = right[dir];}
}