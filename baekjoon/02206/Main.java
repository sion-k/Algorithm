import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 상 하 좌 우 순
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static int N;
	static int M;
	
	static boolean[][] MAZE;
	static boolean[][][] BOOKED;
	// (y, x)까지 부수거나 부수지 않은 상태로 가는 최단 거리
	static int[][][] DIS;
	
	static boolean inRange(int y, int x) {return 1 <= y && y <= N && 1 <= x && x <= M;}
	
	// (1, 1)에서 bfs, (N, M)까지의 최단 거리를 반환한다
	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		// 0 : 벽을 부수지 않음 1 : 이미 부숨
		q.add(new int[] {1, 1, 0});
		BOOKED[1][1][0] = true;
		DIS[1][1][0] = 1;
		
		while (!q.isEmpty()) {
			int[] here = q.poll();
			if(here[0] == N && here[1] == M) {break;}
			for (int move = 0; move < 4; move++) {
				int ty = here[0] + dy[move];
				int tx = here[1] + dx[move];
				int crush = here[2];
				if (inRange(ty, tx) && !BOOKED[ty][tx][crush]) {
					// 그냥 이동할 수 있는 경우
					if (MAZE[ty][tx]) {
						q.offer(new int[] { ty, tx, crush });						
						BOOKED[ty][tx][crush] = true;
						DIS[ty][tx][crush] = DIS[here[0]][here[1]][crush] + 1;
					} else if(crush == 0) { //부술 수 있는 기회가 있는 경우
						q.offer(new int[] { ty, tx, 1 });						
						BOOKED[ty][tx][1] = true;
						DIS[ty][tx][1] = DIS[here[0]][here[1]][crush] + 1;
					}
				}

			}

		}
		return Math.max(DIS[N][M][0], DIS[N][M][1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		MAZE = new boolean[N + 1][M + 1];
		BOOKED = new boolean[N + 1][M + 1][2];
		DIS = new int[N + 1][M + 1][2];
		DIS[N][M][0] = DIS[N][M][1] = -1;
		
		for (int i = 1; i <= N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				MAZE[i][j + 1] = (line[j] == '0');
			}
		}
		br.close();
		
		System.out.println(bfs());
	}

}
