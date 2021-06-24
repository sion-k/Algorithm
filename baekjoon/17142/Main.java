import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] S; // 벽이 있는지 여부
	static int[][] P;// 바이러스를 놓을 수 있는 위치들
	static int empty = 0;
	
	static final int[] dy = {-1, 1, 0, 0}; static final int[] dx = {0, 0, -1, 1};
	
	static final int INF = 2500;
	
	static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < N;}
	
	static int btk(int depth, int toPick, boolean[] picked) {
		if (depth == P.length || toPick == 0) return toPick == 0 ? bfs(picked): INF;
		int min = btk(depth + 1, toPick, picked);
		picked[depth] = true;
		min = Math.min(min, btk(depth + 1, toPick - 1, picked));
		picked[depth] = false;
		return min;
	}
	
	static int bfs(boolean[] picked) {
		Queue<int[]> q = new LinkedList<>();
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
		for (int i = 0; i < picked.length; i++)
			if (picked[i]) {
				q.offer(P[i]);
				dist[P[i][0]][P[i][1]] = 0;
			}
		int cnt = empty;
		int max = 0;
		while (!q.isEmpty() && cnt != 0) {
			int[] p = q.poll();
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (inRange(ny, nx) && S[ny][nx] != 1 && dist[ny][nx] == -1) {
					q.offer(new int[] { ny, nx });
					dist[ny][nx] = dist[y][x] + 1;
					max = Math.max(max, dist[ny][nx]);
					if (S[ny][nx] == 0) cnt--;
				}
			}
		}
		return cnt == 0 ? max : INF;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = new int[N][N];
		int M = Integer.parseInt(st.nextToken());
		int virus = 0;
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < N; x++) {
				S[y][x] = Integer.parseInt(st.nextToken());
				if (S[y][x] == 0) empty++;
				if (S[y][x] == 2) virus++;
			}
		}
		P = new int[virus][2];
		virus = 0;
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				if (S[y][x] == 2) P[virus++] = new int[] { y, x };
		int ret = btk(0, M, new boolean[P.length]);
		System.out.println(ret == INF ? -1 : ret);
	}
	
}
