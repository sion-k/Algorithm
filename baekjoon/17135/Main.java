import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] ENEMY;
	static boolean[] ARC;
	static int D;
	static List<int[]> target;

	static int dfs(int j, int toPick) {
		if (j == M) {
			if (toPick != 0) {return 0;}
			boolean[][] temp = new boolean[N + 1][M];
			for (int i = 0; i < N + 1; i++)
				temp[i] = ENEMY[i].clone();
			int cand = play(); ENEMY = temp;
			return cand;
		}
		int max = 0;
		max = Math.max(max, dfs(j + 1, toPick));
		if (toPick > 0) {
			ARC[j] = true;
			max = Math.max(max, dfs(j + 1, toPick - 1));
			ARC[j] = false;
		}
		return max;
	}

	static int play() {
		// 초기 적의 수 계산
		int enemy = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (ENEMY[i][j]) enemy++;
		int sum = 0;
		while (enemy > 0) {
			int kill = archer();
			sum += kill; enemy -= kill;
			enemy -= move();
		}
		return sum;
	}

	// 궁수들이 현재 위치에서 제거한 적의 수 반환
	static int archer() {
		int sum = 0;
		target = new ArrayList<>();
		// 모든 궁수들이 자신들의 위치에서 타겟팅한다
		for (int j = 0; j < M; j++)
			if (ARC[j]) bfs(N, j);
		// 타겟팅 된 적들을 제거한다. 중복이 있을 수 있다
		for (int[] t : target)
			if (ENEMY[t[0]][t[1]]) {
				ENEMY[t[0]][t[1]] = false;
				sum++;
			}
		return sum;
	}
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// 현재 위치에서 가장 가까운 적, 가장 가까운 적이 여러개라면 왼쪽에 있는 적을 타겟팅한다
	static void bfs(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sy, sx});
		int[][] dist = new int[N + 1][M];
		for (int i = 0; i < N + 1; i++)
			Arrays.fill(dist[i], -1);
		dist[sy][sx] = 0;
		int minDist = N + M;
		int[] cand = null;
		while (!q.isEmpty()) {
			int[] pos = q.poll();
			int y = pos[0]; int x = pos[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				// 범위 밖이거나 이미 예약한 곳이면 무시
				if (!inRange(ny, nx) || dist[ny][nx] != -1) {continue;}
				dist[ny][nx] = dist[y][x] + 1;
				// 최대 사거리보다 작다면 큐에 추가한다
				if (dist[ny][nx] < D && dist[ny][nx] < minDist)
					q.offer(new int[] {ny, nx});
				// 만약 적이 있고, 최단거리 이하라면 타겟팅 후보와 비교
				if (ENEMY[ny][nx] && dist[ny][nx] <= minDist) {
					minDist = dist[ny][nx];
					if (cand == null || nx < cand[1])
						cand = new int[] {ny, nx};
				}
			}
		}
		if (cand != null) target.add(cand);
	}

	// 모든 적들을 아래로 한칸 이동, 성에 도달하는 적들의 수를 반환한다
	static int move() {
		int sum = 0;
		// 성 바로 윗 칸에 위치하는 적들은 성에 도달한다
		for (int j = 0; j < M; j++)
			if (ENEMY[N - 1][j]) {ENEMY[N - 1][j] = false; sum++;}
		for (int i = N - 1; i > 0; i--)
			for (int j = 0; j < M; j++)
				if (ENEMY[i - 1][j]) {
					ENEMY[i][j] = true;
					ENEMY[i - 1][j] = false;
				}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		ENEMY = new boolean[N + 1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				ENEMY[i][j] = st.nextToken().equals("1");
		}
		ARC = new boolean[M];
		System.out.println(dfs(0, 3));
	}

}
