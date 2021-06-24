import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; // 정점 수
	static int[][] HEIGHT;
	static boolean[][] VISIT;// DFS용
	static int waterLevel = 1;
	
	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static int dfsAll() {
		int cnt = 0;// dfs(y, x) 호출 횟수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!VISIT[i][j] && HEIGHT[i][j] > waterLevel) {
					dfs(i, j); cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void dfs(int y, int x) {
		VISIT[y][x] = true;
		for (int move = 0; move < 4; move++) {
			int ty = y + dy[move]; int tx = x + dx[move];
			if(inRange(ty, tx) && HEIGHT[ty][tx] > waterLevel && !VISIT[ty][tx]) {
				dfs(ty, tx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		HEIGHT = new int[N][N];
		VISIT = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				HEIGHT[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		int max = 1; int nArea = 0;
		while((nArea = dfsAll()) != 0) {
			VISIT = new boolean[N][N];
			max = Math.max(max, nArea);
			waterLevel++;
		}
		System.out.println(max);
	}

}
