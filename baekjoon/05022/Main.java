import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static Pair[] A; static Pair[] B;
    static char[][] C;

    static int[][] booked;
    
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static final int INF = 987654321;

    static boolean inRange(int y, int x) { return 0 <= y && y <= N && 0 <= x && x <= M; }

    // u와 v간의 최단 거리 반환
    // booked를 초기화한 뒤, 해당 경로를 역추적해서 방문 처리 해놓는다.
    static int bfs(Pair u, Pair v, char c) {
        int sy = u.y; int sx = u.x;
        int ey = v.y; int ex = v.x;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { sy, sx });
        booked[sy][sx] = 0;
        int[][][] prev = new int[N + 1][M + 1][];
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int y = p[0]; int x = p[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (!inRange(ny, nx) || booked[ny][nx] != -1) continue;
                if (C[ny][nx] != 0 && C[ny][nx] != c) continue;
                q.offer(new int[] { ny, nx });
                booked[ny][nx] = booked[y][x] + 1;
                prev[ny][nx] = new int[] { y, x };
            }
        }
        int ret = booked[ey][ex] == -1 ? INF : booked[ey][ex];
        booked = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(booked[i], -1);
        int ty = ey; int tx = ex;
        while (prev[ty][tx] != null) {
            booked[ty][tx] = 0;
            int ny = prev[ty][tx][0]; int nx = prev[ty][tx][1];
            ty = ny; tx = nx;
        }
        booked[sy][sx] = 0;
        return ret;
    }

    // A1, A2를 잇는 위쪽 경로(ㄱ)를 방문처리한다
    // 만약 중간에 B[0]나 B[1]이 겹칠경우 true 반환
    static boolean upperOverlaps() {
        int sy = A[0].y; int ey = A[1].y;
        if (sy > ey) { int temp = sy; sy = ey; ey = temp; }
        for (int y = sy; y <= ey; y++) {
            if (y == B[0].y && A[1].x == B[0].x) return true;
            if (y == B[1].y && A[1].x == B[1].x) return true;
            booked[y][A[1].x] = 0;
        }
        int sx = A[0].x; int ex = A[1].x;
        if (sx > ex) { int temp = sx; sx = ex; ex = temp; }
        for (int x = sx; x <= ex; x++) {
            if (A[0].y == B[0].y && x == B[0].x) return true;
            if (A[0].y == B[1].y && x == B[1].x) return true;
            booked[A[0].y][x] = 0;
        }
        return false;
    }

    // A1, A2를 잇는 아래쪽 경로(ㄴ)를 방문처리한다
    // 만약 중간에 B[0]나 B[1]이 겹칠경우 false 반환
    static boolean lowerOverlaps() {
        int sy = A[0].y; int ey = A[1].y;
        if (sy > ey) { int temp = sy; sy = ey; ey = temp; }
        for (int y = sy; y <= ey; y++) {
            if (y == B[0].y && A[0].x == B[0].x) return true;
            if (y == B[1].y && A[0].x == B[1].x) return true;
            booked[y][A[0].x] = 0;
        }
        int sx = A[0].x; int ex = A[1].x;
        if (sx > ex) { int temp = sx; sx = ex; ex = temp; }
        for (int x = sx; x <= ex; x++) {
            if (A[1].y == B[0].y && x == B[0].x) return true;
            if (A[1].y == B[1].y && x == B[1].x) return true;
            booked[A[1].y][x] = 0;
        }
        return false;
    }

    // u와 v간의 맨해튼 거리 반환
    static int dist(Pair u, Pair v) {
        return Math.abs(u.y - v.y) + Math.abs(u.x - v.x);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new Pair[2]; B = new Pair[2];
        C = new char[N + 1][M + 1];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            A[i] = new Pair(y, x);
            C[y][x] = 'A';
        }
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            B[i] = new Pair(y, x);
            C[y][x] = 'B';
        }
        Arrays.sort(A); Arrays.sort(B);
        booked = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(booked[i], -1);
        int min = bfs(A[0], A[1], 'A') + bfs(B[0], B[1], 'B');
        // (A1, A2)를 위쪽 경로(ㄱ)로 연결
        booked = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(booked[i], -1);
        if (!upperOverlaps()) min = Math.min(min, dist(A[0], A[1]) + bfs(B[0], B[1], 'B'));
        // (A1, A2)를 아래쪽 경로(ㄴ)로 연결
        booked = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(booked[i], -1);
        if (!lowerOverlaps()) min = Math.min(min, dist(A[0], A[1]) + bfs(B[0], B[1], 'B'));
        A[0].swap(B[0]);
        A[1].swap(B[1]);
        C = new char[N + 1][M + 1];
        C[A[0].y][A[0].x] = 'A';
        C[A[1].y][A[1].x] = 'A';
        C[B[0].y][B[0].x] = 'B';
        C[B[1].y][B[1].x] = 'B';
        booked = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(booked[i], -1);
        min = Math.min(min, bfs(A[0], A[1], 'A') + bfs(B[0], B[1], 'B'));
        // (A1, A2)를 위쪽 경로(ㄱ)로 연결
        booked = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(booked[i], -1);
        if (!upperOverlaps()) min = Math.min(min, dist(A[0], A[1]) + bfs(B[0], B[1], 'B'));
        // (A1, A2)를 아래쪽 경로(ㄴ)로 연결
        booked = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(booked[i], -1);
        if (!lowerOverlaps()) min = Math.min(min, dist(A[0], A[1]) + bfs(B[0], B[1], 'B'));
        System.out.println(min >= INF ? "IMPOSSIBLE" : min);
    }

}
class Pair implements Comparable<Pair> {
    int y, x;

    Pair(int y, int x) { this.y = y; this.x = x; }

    void swap(Pair o) {
        int temp = y; y = o.y; o.y = temp;
        temp = x; x = o.x; o.x = temp;
    }

    @Override
    public int compareTo(Pair o) { return y == o.y ? x - o.x : y - o.y; }
}
