import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] S;
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }
	
	// S[0][0]에서 시작해서 S[N - 1][N - 1]까지
	// 가장 작은 값이 l, 가장 큰 값이 r이 되게 이동할 수 있는지 여부
	static boolean f(int l, int r) {
		if (S[0][0] < l || r < S[0][0]) return false;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0 });
		boolean[][] booked = new boolean[N][N];
		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || booked[ny][nx] || S[ny][nx] < l || r < S[ny][nx]) continue;
				if (ny == N - 1 && nx == N - 1) return true;
				q.offer(new int[] { ny, nx });
				booked[ny][nx] = true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		int min = 200;
		for (int l = 0; l <= 200; l++) {
			if (f(l, l)) { min = 0; break; }
			if (!f(l, 200)) continue;
			// f(l, lo) = false && f(l, hi) = true인 hi지점을 찾는다
			int lo = l; int hi = 200;
			while (lo + 1 < hi) {
				int mid = (lo + hi) / 2;
				if (f(l, mid)) hi = mid;
				else lo = mid;
			}
			min = Math.min(min, hi - l);
		}
		System.out.println(min);
	}

}
