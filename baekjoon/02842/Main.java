import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static char[][] C;
    static int[][] S;

    static final int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static final int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

    // lo이상, hi이하의 칸만 사용해서 모든 집에 배달을 하는 것이 가능한지 여부 반환
    static boolean f(int lo, int hi) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] booked = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (C[i][j] == 'P') {
                    if (lo <= S[i][j] && S[i][j] <= hi) {
                        q.offer(new int[] { i, j });
                        booked[i][j] = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int y = p[0]; int x = p[1];
            for (int d = 0; d < 8; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (!inRange(ny, nx) || booked[ny][nx] || S[ny][nx] < lo || S[ny][nx] > hi) continue;
                q.offer(new int[] { ny, nx });
                booked[ny][nx] = true;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (C[i][j] == 'K' && !booked[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        C = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) C[i][j] = line.charAt(j);
        }
        ArrayList<Integer> H = new ArrayList<>();
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
                H.add(S[i][j]);
            }
        }
        Collections.sort(H);
        int min = 1000000;
        for (int i : H) {
            if (f(i, i)) min = 0;
            if (!f(i, 1000000)) continue;
            int lo = i; int hi = 1000000;
            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;
                if (f(i, mid)) hi = mid;
                else lo = mid;
            }
            min = Math.min(min, hi - i);
        }
        bw.append(min).append("\n");
        System.out.print(bw);
    }

}
