import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] BOARD;
	static boolean[] VISIT = new boolean[26];
	static int R; static int C;
	
	// 상하좌우
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	// (y, x)에서 시작하는 최대 경로
	static int maxPath(int y, int x) {
		if (y < 0 || R <= y || x < 0 || C <= x) {return 0;}
		if (VISIT[BOARD[y][x]]) {return 0;}
		int max = 0; 
		VISIT[BOARD[y][x]] = true;
		for (int next = 0; next < 4; next++) {
			max = Math.max(max, maxPath(y + dy[next], x + dx[next]));
		}
		VISIT[BOARD[y][x]] = false;
		return 1 + max; // (y, x)는 방문 가능하므로
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		BOARD = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {BOARD[i][j] = row[j] - 'A';}
		}
		System.out.println(maxPath(0, 0));
	}

}
