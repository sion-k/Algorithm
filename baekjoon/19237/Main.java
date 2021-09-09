import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Shark[] S = new Shark[M + 1];
        Shark.N = N; Shark.M = M; Shark.K = K;
        Shark.S = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                int index = Integer.parseInt(st.nextToken());
                S[index] = new Shark(index, 0, y, x);
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int k = 1; k <= M; k++) S[k].dir = Integer.parseInt(st.nextToken()) - 1;
        for (int k = 1; k <= M; k++) {
            int[][] pri = new int[4][4];
            for (int d = 0; d < 4; d++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 4; j++) pri[d][j] = Integer.parseInt(st.nextToken()) - 1;
            }
            S[k].pri = pri;
        }
	}

}

class Shark {
    static int N, M, K;
    // S[i][j]에 존재하는 상어 혹은 상어의 냄새 인덱스
    static int[][] S;

    static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

    int index, dir;
    int[][] pri;
    Deque<int[]> path;
    boolean alive;

    Shark(int i, int d, int y, int x) { 
        index = i; dir = d;
        path = new LinkedList<>();
        path.offer(new int[] { y, x });
        S[y][x] = i;
        alive = true;
    }

    void move(Queue<Pair> q) {
        if (!alive) {
            if (!path.isEmpty()) {
                int[] p = path.poll();
                int y = p[0]; int x = p[1];
                S[y][x] = 0;        
            }
            return;
        }
        int[] p = path.peek();
        int y = p[0]; int x = p[1];
        // 아무 냄새가 없는 칸을 먼저 찾는다.
        for (int i = 0; i < 4; i++) {
            int d = pri[dir][i];
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx) && S[ny][nx] == 0) {
                q.offer(new Pair(index, ny, nx));
                return;
            }
        }
        // 기존 방향으로 돌아간다
        for (int i = 0; i < 4; i++) {
            int d = pri[dir][i];
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx) && S[ny][nx] == index) {
                q.offer(new Pair(index, ny, nx));
                return;
            }
        }
    }

    @Override
    public String toString() { return String.format("(%d, %d)", index , dir); }

}
class Pair {
    int i, y, x;

    Pair (int i, int y, int x) { this.i = i; this.y = y; this.x = x; }
}
