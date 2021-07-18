import java.io.*;
import java.util.*;

public class Main {
	static int H, W;
	static Tuple[][] S; // 지형에 위치하는 유닛 저장
	static int[][] R; // 지형의 험준도 저장
	static Tuple[] U;
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < H && 0 <= x && x < W;
	}
	
	static void move(int u, int y, int x) {
		// 목표 지점에 다른 유닛이 있거나
		if (S[y][x] != null) return;
		// 목표 지점이 이동 불가 지형이거나
		if (R[y][x] == -1) return;
		// 이동력의 한계로 인해 목표 지점에 도달하는 경로가 존재하지 않는 경우
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(U[u].y, U[u].x, 0));
		int[][] dist = new int[H][W];
		for (int i = 0; i < H; i++)
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		dist[U[u].y][U[u].x] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int hy = p.y; int hx = p.x; int hd = p.d;
			if (dist[hy][hx] < hd) continue;
			// 이동시키기 전에 다른 세력의 유닛과 인접했는지 확인
			// 단 시작 지점인 경우 예외
			boolean flag = false;
			for (int d = 0; d < 4; d++) {
				int ny = hy + dy[d]; int nx = hx + dx[d];
				if (!inRange(ny, nx)) continue;
				if (S[ny][nx] != null && S[ny][nx].t != U[u].t) {
					flag = true; break;
				}
			}
			if (hd != 0 && flag) continue;
			for (int d = 0; d < 4; d++) {
				int ny = hy + dy[d]; int nx = hx + dx[d];
				// 범위 밖이거나 이동할 수 없는 경우
				if (!inRange(ny, nx) || R[ny][nx] == -1) continue;
				// 세력이 다른 유닛이 있는 경우
				if (S[ny][nx] != null && U[u].t != S[ny][nx].t) continue;
				int nd = hd + R[ny][nx];
				// 한 번의 약진에 소모할 수 있는 행동력을 넘어가는 경우
				if (nd > U[u].m) continue;
				if (dist[ny][nx] > nd) {
					dist[ny][nx] = nd;
					pq.offer(new Pair(ny, nx, nd));
				}
			}
		}
		// 유닛을 이동시킨다
		if (dist[y][x] != Integer.MAX_VALUE) {
			S[U[u].y][U[u].x] = null;
			U[u].y = y; U[u].x = x;
			S[U[u].y][U[u].x] = U[u];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		S = new Tuple[H][W];
		R = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) R[i][j] = Integer.parseInt(st.nextToken());
		}
		int[] temp = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) temp[i] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++) 
				R[i][j] = temp[R[i][j]];
		int M = Integer.parseInt(br.readLine());
		U = new Tuple[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			U[i] = new Tuple(m, t, y, x);
			S[y][x] = U[i];
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			move(u, y, x);
		}
		for (int i = 1; i <= M; i++)
			bw.append(U[i].y).append(" ").append(U[i].x).append("\n");
		System.out.print(bw);
	}

}

class Pair implements Comparable<Pair> {
	int y, x, d;
	
	Pair(int y, int x, int d) { this.y = y; this.x = x; this.d = d; }
	
	@Override
	public int compareTo(Pair o) { return d - o.d; }
	
}

class Tuple {
	int m, t, y, x;
	
	Tuple(int m, int t, int y, int x) {
		this.m = m; this.t = t; this.y = y; this.x = x;
	}
	
}
