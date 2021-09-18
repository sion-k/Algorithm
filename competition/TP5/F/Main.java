import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static boolean found = false;

    static final int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static final int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

    static void print(Stack<int[]> S) {
        StringBuilder bw = new StringBuilder();
        for (int[] p : S) bw.append(String.format("%d %d\n", p[0] + 1, p[1] + 1));
        System.out.print(bw);
        found = true;
    }

    static int count(int y, int x, int[][] visit) {
        int ret = 0;
        for (int d = 0; d < 8; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (!inRange(ny, nx) || visit[ny][nx] == 2) continue;
            ret++;
        }
        return ret;
    }

    static void btk(int y, int x, int k, int[][] visit, Stack<int[]> S) {
        if (found) return;
        if (k == K) { print(S); return; }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int d = 0; d < 8; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (!inRange(ny, nx) || visit[ny][nx] == 2) continue;
            pq.offer(new Pair(d, count(ny, nx, visit)));
        }
        while (!pq.isEmpty()) {
            if (found) break;
            int d = pq.poll().index;
            int ny = y + dy[d]; int nx = x + dx[d];
            visit[ny][nx]++;
            S.push(new int[] { ny, nx });
            btk(ny, nx, k + (visit[ny][nx] == 1 ? 1 : 0), visit, S);
            visit[ny][nx]--;
            S.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = N * N;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int y = Integer.parseInt(st.nextToken()) - 1;
        int x = Integer.parseInt(st.nextToken()) - 1;
        int[][] visit = new int[N][N];
        visit[y][x] = 1;
        Stack<int[]> S = new Stack<>();
        S.push(new int[] { y, x });
        btk(y, x, 1, visit, S);
        if (!found) System.out.println(-1);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;

    Pair (int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return value - o.value; }
}
