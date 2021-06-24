import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int L; //체스판 한 변의 길이
	static boolean[][] BOOKED;
	static int[][] DIST;
	static int gY; static int gX; //목표 (y, x)
	
	// 맨 왼쪽 위부터 8개의 움직임
	static int[] dy = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dx = { -1, 1, -2, 2, -2, 2, -1, 1 };
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < L && 0 <= x && x < L;
	}
	
	// (y, x)에서 (gY, gX)까지의 최단 거리 반환
	static int bfs(int y, int x) {
		if (y == gY && x == gX) {return 0;}
		Queue<int[]> q = new LinkedList<>();
		DIST[y][x] = 0; BOOKED[y][x] = true;
		q.offer(new int[] {y, x});
		
		while (!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 8; next++) {
				int ty = here[0] + dy[next]; int tx = here[1] + dx[next];
				if (inRange(ty, tx) && !BOOKED[ty][tx]) {
					if (ty == gY && tx == gX) {
						return DIST[here[0]][here[1]] + 1;
					}
					q.offer(new int[] {ty, tx});
					BOOKED[ty][tx] = true;
					DIST[ty][tx] = DIST[here[0]][here[1]] + 1;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			L = Integer.parseInt(br.readLine());
			BOOKED = new boolean[L][L];
			DIST = new int[L][L];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			gY = Integer.parseInt(st.nextToken());
			gX = Integer.parseInt(st.nextToken());
			System.out.println(bfs(y, x));
		}
		br.close();
	}

}
