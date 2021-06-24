import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static final int N = 12; static final int M = 6;
	static char[][] MAP = new char[N][M];
	static boolean[][] VISIT;
	static boolean[][] SELECT = new boolean[N][M];

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// 1번 이상 4정점 이상을 방문한 DFS가 호출되면 1연쇄 추가
	static boolean DFSAll() {
		VISIT = new boolean[N][M];
		boolean boom = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (MAP[i][j] != '.' && !VISIT[i][j]) {
					if (DFS(i, j) >= 4) {
						remove();
						boom = true;
					} else {
						SELECT = new boolean[N][M];
					}
				}
			}
		}
		return boom;
	}

	// 순회한 뿌요의 개수를 반환
	static int DFS(int y, int x) {
		VISIT[y][x] = true; SELECT[y][x] = true;
		int cnt = 0;
		for (int next = 0; next < 4; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if (inRange(ty, tx) && MAP[ty][tx] == MAP[y][x] && !VISIT[ty][tx]) {
				cnt += DFS(ty, tx);
			}
		}
		return 1 + cnt;
	}

	// 선택 되어 있는 뿌요들을 제거
	static void remove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (SELECT[i][j]) {MAP[i][j] = '.'; SELECT[i][j] = false;}
			}
		}
	}

	// 공중에 떠있는 뿌요들을 떨어뜨린다
	static void gravity() {
		char[][] temp = new char[N][M];
		for (char[] row : temp) {Arrays.fill(row, '.');}
		for (int j = 0; j < M; j++) {
			ArrayList<Character> col = new ArrayList<>();
			for (int i = N - 1; i >= 0; i--) {
				if (MAP[i][j] != '.') {col.add(MAP[i][j]);}
			}
			int floor = N - 1;
			for (char ch : col) {temp[floor][j] = ch; floor--;}
		}
		MAP = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {MAP[i][j] = row.charAt(j);}
		}
		int boom = 0;
		while (DFSAll()) {boom++; gravity();}
		System.out.println(boom);
	}

}
