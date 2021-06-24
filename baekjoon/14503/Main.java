import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 벽이 없어서 이동 가능한지 여부
	static boolean[][] MOVEABLE;
	
	static class Robot {
		int y; int x; int dir;
		boolean[][] cleaned;
		
		// 현재 바라보는 방향이 북 동 남 서 순으로 왼쪽의 위치
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
		
		// 반시계 방향 회전
		public void rotate() {if(--dir < 0) {dir = 3;}}
		
		// 로봇 청소기의 현재 위치에서 청소를 시작해서 청소할 수 있는 칸의 개수 반환
		public int clean() {
			int cnt = 0;
			//청소 되어 있지 않으면 청소한다
			if(!cleaned[y][x]) {cleaned[y][x] = true; cnt++;}
			
			for(int d = 0; d < 4; d++) {
				int ty = y + LDY[dir]; int tx = x + LDX[dir];
				rotate();
				if(inRange(ty, tx) && !cleaned[ty][tx] && MOVEABLE[ty][tx]) {
					y = ty; x = tx;
					return cnt + clean();
				}
			}
			// 네 방향 모두 청소가 이미 되어있거나 벽인 경우 후진
			int ty = y + BDY[dir]; int tx = x + BDX[dir];
			if(inRange(ty, tx) && MOVEABLE[ty][tx]) {
				y = ty; x = tx;
				return cnt + clean();
			}
			// 후진 할 수 없으면 종료
			return cnt;
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
