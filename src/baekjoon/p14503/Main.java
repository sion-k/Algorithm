package baekjoon.p14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// ���� ��� �̵� �������� ����
	static boolean[][] MOVEABLE;
	
	static class Robot {
		int y; int x; int dir;
		boolean[][] cleaned;
		
		// ���� �ٶ󺸴� ������ �� �� �� �� ������ ������ ��ġ
		static int[] LDY = { 0, -1, 0, 1 };
		static int[] LDX = { -1, 0, 1, 0 };
		static int[] BDY = { 1, 0, -1, 0 };
		static int[] BDX = { 0, -1, 0, 1 };
		
		static boolean inRange(int y, int x) {
			return 0 <= y && y < MOVEABLE.length && 0 <= x && x < MOVEABLE[0].length;
		}
		
		public Robot(int y, int x, int dir) {
			this.y = y; this.x = x; this.dir = dir;
		}
		
		// �ݽð� ���� ȸ��
		public void rotate() {if(--dir < 0) {dir = 3;}}
		
		// �κ� û�ұ��� ���� ��ġ���� û�Ҹ� �����ؼ� û���� �� �ִ� ĭ�� ���� ��ȯ
		public int clean() {
			int cnt = 0;
			//û�� �Ǿ� ���� ������ û���Ѵ�
			if(!cleaned[y][x]) {cleaned[y][x] = true; cnt++;}
			
			for(int d = 0; d < 4; d++) {
				int ty = y + LDY[dir]; int tx = x + LDX[dir];
				rotate();
				if(inRange(ty, tx) && !cleaned[ty][tx] && MOVEABLE[ty][tx]) {
					y = ty; x = tx;
					return cnt + clean();
				}
			}
			// �� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���� ��� ����
			int ty = y + BDY[dir]; int tx = x + BDX[dir];
			if(inRange(ty, tx) && MOVEABLE[ty][tx]) {
				y = ty; x = tx;
				return cnt + clean();
			}
			// ���� �� �� ������ ����
			return cnt;
		}
		
		public String toString() {
			return "("+ y +", " + x + ", " + dir + ")";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int DIR = Integer.parseInt(st.nextToken());
		
		Robot r = new Robot(Y, X, DIR);
		r.cleaned = new boolean[N][M];
		MOVEABLE = new boolean[N][M];
		
		for (int i = 0; i < MOVEABLE.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < MOVEABLE[i].length; j++) {
				MOVEABLE[i][j] = (st.nextToken().equals("0"));
			}
		}
		br.close();
		System.out.println(r.clean());
	}

}