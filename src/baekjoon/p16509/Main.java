package baekjoon.p16509;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Pos {
		int y; int x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static boolean reachable(int y, int x, int moveWay) {
		for (int i = 0; i < 2; i++) {
			int ty = mdy[moveWay][i]; int tx = mdx[moveWay][i];
			if (ty == yK && tx == xK) {return false;}
		}
		return true;
	}
	
	static int BFS() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(yP, xP));
		DIS[yP][xP] = 0;
		
		while(!q.isEmpty()) {
			Pos here = q.poll();
			for (int i = 0; i < 8; i++) {
				int ty = here.y + dy[i]; int tx = here.x + dx[i];
				System.out.println(ty + " " + tx);
				if (0 < ty || 10 <= ty || 0 < tx || 9 <= tx) {continue;}
				if (!reachable(here.y, here.x, i)) {continue;}
				if (ty == yK && tx == xK) {
					return DIS[here.y][here.x] + 1; 
				}
				if (DIS[ty][tx] == -1) {
					q.offer(new Pos(ty, tx));
					DIS[ty][tx] = DIS[here.y][here.x] + 1;
				}
			}
		}
		
		return -1;
	}
	// -1 ¹Ì°è»ê
	static int DIS[][] = new int[10][9];
	static {
		for (int i = 0; i < 10; i++) {
			Arrays.fill(DIS[i], -1);
		}
	}
	static final int[] dy = { -3, -3, -2, -2, 2, 2, 3, 3 };
	static final int[] dx = { -2, 2, -3, 3, -3, 3, -2, 2 };
	
	static final int[][] mdy = { { -1, -2 }, { -1, -2 }, { 0, -1 }, { 0, -1 }, { 0, 1 }, { 0, 1 }, { 1, 2 }, { 1, 2 } };
	static final int[][] mdx = { { 0, -1 }, { 0, 1 }, { -1, -2 }, { 1, 2 }, { -1, -2 }, { 1, 2 }, { 0, -1 }, { 0, 1 } };
	
	static int yP; static int xP;
	static int yK; static int xK;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		yP = sc.nextInt(); xP = sc.nextInt();
		yK = sc.nextInt(); xK = sc.nextInt();
		sc.close();
		
		System.out.println(BFS());
	}

}
