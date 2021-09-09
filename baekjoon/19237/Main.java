import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    // 인덱스별 상어에 대한 참조
    static Tuple[] S;
    // 격자에 대한 정보
    static Tuple[][] MAP;

    // 상어 k번째 상어의 d번 방향 이동에 대한 우선순위 저장
    static int[][][] P;

    static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

    // 현재 t초일 때, k번째 상어가 위치한 곳을 찾는다.
    // 없으면 0짜리 배열 반환
    static int[] find(int k, int t) {
        for (int y = 0; y < N; y++)
            for (int x = 0; x < N; x++)
                if (MAP[y][x].index == k && MAP[y][x].time == t)
                    return new int[] { y, x };
        return new int[0];
    }

    // 현재 t초일 때, k번째 상어를 옮기는 시도를 한다.
    static void move(int k, int t, Queue<int[]> q) {
        int[] p = find(k, t);
        if (p.length == 0) return;
        int y = p[0]; int x = p[1];
        Tuple u = MAP[y][x];
        // 아무 냄새가 없는 칸으로 간다.
        for (int i = 0; i < 4; i++) {
            int d = P[k][u.dir][i];
            int ny = y + dy[d]; int nx = x + dx[d];
            // 해당 위치에 냄새가 생긴지 K초가 지났다면 냄새가 없다.
            // 혹은 애초에 냄새가 없었을 수도 있다.
            if (inRange(ny, nx) && (MAP[ny][nx].time == 0 || MAP[ny][nx].time + K <= t)) {
                // 한 번에 옮기기 위해서 후보에 넣어준다.
                q.offer(new int[] { u.index, d, ny, nx });
                return;
            }
        }
        // 자신의 냄새가 있는 칸으로 간다.
        for (int i = 0; i < 4; i++) {
            int d = P[k][u.dir][i];
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx) && u.index == MAP[ny][nx].index) {
                // 한 번에 옮기기 위해서 후보에 넣어준다.
                q.offer(new int[] { u.index, d, ny, nx });
                return;
            }
        }
    }

    // 현재 t초일 때 모두 옮긴다.
    static void moveAll(int t) {
        Queue<int[]> q = new LinkedList<>();
        for (int k = M; k >= 1; k--) move(k, t, q);
        for (int[] p : q) {
            int i = p[0]; int d = p[1]; int y = p[2]; int x = p[3];
            Tuple a = MAP[y][x];
            a.index = i; a.time = t + 1; a.dir = d;
        }
    }

    static void print() {
        StringBuilder bw = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) bw.append(MAP[i][j]).append(" ");
            bw.append("\n");
        }
        System.out.println(bw);
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new Tuple[M + 1];
        MAP = new Tuple[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                int index = Integer.parseInt(st.nextToken());
                MAP[y][x] = new Tuple(index, index == 0 ? 0 : 1, 0);
                if (index != 0) S[index] = MAP[y][x];
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int k = 1; k <= M; k++) S[k].dir = Integer.parseInt(st.nextToken()) - 1;
        P = new int[M + 1][4][4];
        for (int k = 1; k <= M; k++) {
            for (int d = 0; d < 4; d++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int i = 0; i < 4; i++) P[k][d][i] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
        int ret = -1;
        for (int t = 1; t <= 1001; t++) {
            moveAll(t);
            int cnt = 0;
            for (int k = 1; k <= M; k++)
                if (find(k, t).length != 0) cnt++;
            if (cnt == 1) { ret = t - 1; break; }
        }
        System.out.println(ret);
    }

}
// 어떤 칸에 대해 상어나 상어의 냄새에 대한 정보를 저장한다
class Tuple {
    // 0일경우 상어가 없는 경우. 아니라면 상어 혹은 상어의 냄새
    int index; 
    // 1초부터 시작해서 해당 냄새가 생긴 시점
    // 현재 상어의 위치한 곳에도 냄새가 있다고 생각한다.
    int time; 
    // 상어가 바라보는 방향
    int dir;

    Tuple(int i, int t, int d) { index = i; time = t; dir = d; }
    
    @Override
    public String toString() { return String.format("(%d, %d, %d)", index, time, dir); }
}