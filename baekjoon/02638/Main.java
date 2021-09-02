import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	// 치즈인지 여부
	static boolean[][] S;
	// 바깥쪽인지 여부, 치즈가 있는 칸 또한 바깥쪽이 아님
	static boolean[][] R;
	
	// 치즈의 좌표값들을 저장
	static Queue<int[]> C = new LinkedList<>();
	// 새로 추가된 바깥쪽 좌표값들을 저장
	static Queue<int[]> D = new LinkedList<>();
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static void melt() {
		int range = C.size();
		for (int i = 0; i < range; i++) {
			int[] p = C.poll();
			int y = p[0]; int x = p[1];
			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || !R[ny][nx]) continue;
				cnt++;
			}
			if (cnt < 2) C.offer(p);
			else {
				S[y][x] = false;
				D.offer(p);
			}
		}
		spread();
	}
	
	// 방금 막 녹은 칸을 시작으로 바깥쪽을 갱신시킨다
	static void spread() {
		for (int[] p : D) {
			int y = p[0]; int x = p[1];
			R[y][x] = true;
		}
		while (!D.isEmpty()) {
			int[] p = D.poll();
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || S[ny][nx] || R[ny][nx]) continue;
				D.offer(new int[] { ny, nx });
				R[ny][nx] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				if (st.nextToken().equals("1")) {
					S[i][j] = true;
					C.offer(new int[] { i, j });
				}
		}
		// 치즈의 바깥쪽을 초기화한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] booked = new boolean[N][M];
		R = new boolean[N][M];
		q.offer(new int[] { 0, 0 });
		booked[0][0] = true;
		R[0][0] = true;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || S[ny][nx] || booked[ny][nx]) continue;
				q.offer(new int[] { ny, nx });
				booked[ny][nx] = true;
				R[ny][nx] = true;
			}
		}
		int t = 0;
		while (!C.isEmpty()) {
			melt();
			t++;
		}
		System.out.println(t);
	}

}
