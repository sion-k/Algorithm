import java.util.*;
import java.io.*;

public class Main {
    static int A, B;
    static int N, M;

    static boolean inRange(int x) { return 0 <= x && x <= 100000; }

    static int next(int here, int d) {
        switch(d) {
            case 0 : return here - 1;
            case 1 : return here + 1;
            case 2 : return here - A;
            case 3 : return here + A;
            case 4 : return here - B;
            case 5 : return here + B;
            case 6 : return here * A;
            case 7 : return here * B;
        }
        return -1;
    }

    static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        int[] dist = new int[100001];
        Arrays.fill(dist, -1);
        dist[N] = 0;
        while (!q.isEmpty()) {
            int here = q.poll();
            for (int d = 0; d < 8; d++) {
                int there = next(here, d);
                if (!inRange(there) || dist[there] != -1) continue;
                q.offer(there);
                dist[there] = dist[here] + 1;
            }
        }
        return dist[M];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bw.append(bfs());
        System.out.println(bw);
    }

}
