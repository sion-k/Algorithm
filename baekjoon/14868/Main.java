import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] S;
	static boolean[][] booked;
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }
	
	static void bfs(int sy, int sx, int k) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });
		booked[sy][sx] = true;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || S[ny][nx] > k || booked[ny][nx]) continue;
				q.offer(new int[] { ny, nx });
				booked[ny][nx] = true;
			}
		}
	}
	
	// 현재 k번째 해일 때 모두 만났는지 확인
	static boolean bfsAll(int k) {
		int cnt = 0;
		booked = new boolean[N][N];
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				if (!booked[y][x] && S[y][x] <= k) {
					if (cnt != 0) return false;
					bfs(y, x, k);
					cnt++;
				}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<int[]> q = new LinkedList<>();
		S = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(S[i], -1);
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			q.offer(new int[] { y, x });
			S[y][x] = 0;
		}
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || S[ny][nx] != -1) continue;
				q.offer(new int[] { ny, nx });
				S[ny][nx] = S[y][x] + 1;
			}
		}
		if (bfsAll(0)) {
			System.out.println(0);
			return;
		}
		int lo = 0; int hi = 1999;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (bfsAll(mid)) hi = mid;
			else lo = mid;
		}
		System.out.println(hi);
	}

}
