package baekjoon.p16509;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static boolean[][] BOOKED = new boolean[10][9];
	static int[][] DIST = new int[10][9];
	
	static int yK; static int xK;
	
	// �� ���� ���� ������ 8���� �̵��ϴ� ��ġ
	static final int[] dy = { -3, -3, -2, -2, 2, 2, 3, 3 };
	static final int[] dx = { -2, 2, -3, 3, -3, 3, -2, 2 };
	
	// 8���� �̵� ����� ���� ��ֹ��� �����ϸ� �ȵǴ� (y, x) ���� ��
	static final int[][][] path =
	{ {{-1,  0}, {-2, -1}}, {{-1,  0}, {-2,  1}},
	  {{ 0, -1}, {-1, -2}}, {{ 0,  1}, {-1,  2}},
	  {{ 0, -1}, { 1, -2}}, {{ 0,  1}, { 1,  2}},
	  {{ 1,  0}, { 2, -1}}, {{ 1,  0}, { 2,  1}}
	};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < 10 && 0 <= x && x < 9;
	}
	
	// (y, x)���� moveWay�� �̵��ϴ°� �������� ��ȯ
	static boolean reachable(int y, int x, int moveWay) {
		int ty = y + dy[moveWay]; int tx = x + dx[moveWay];
		if(!inRange(ty, tx)) {return false;}
		for(int p = 0; p < 2; p++) {
			int yP = y + path[moveWay][p][0];
			int xP = x + path[moveWay][p][1];
			if (yP == yK && xP == xK) {return false;}
		}
		return true;
	}
	
	// (y, x)���� �����ؼ� ���� �տ��� �����ϴ� �ּ� �Ÿ� ��ȯ
	static int bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {y, x});
		DIST[y][x] = 0;
		BOOKED[y][x] = true;
		
		while(!q.isEmpty()) {
			int[] here = q.poll();
			for (int moveWay = 0; moveWay < 8; moveWay++) {
				int ty = here[0] + dy[moveWay]; int tx = here[1] + dx[moveWay];
				if (reachable(here[0], here[1], moveWay) && !BOOKED[ty][tx]) {
					if (ty == yK && tx == xK) {
						return DIST[here[0]][here[1]] + 1;
					}
					q.offer(new int[] {ty, tx});
					DIST[ty][tx] = DIST[here[0]][here[1]] + 1;
					BOOKED[ty][tx] = true;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int yP = sc.nextInt(); int xP = sc.nextInt();
		yK = sc.nextInt(); xK = sc.nextInt();
		sc.close();
		System.out.println(bfs(yP, xP));
	}

}