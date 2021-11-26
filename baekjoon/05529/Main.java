import java.util.*;
import java.io.*;

public class Main {
    static final long INF = Long.MAX_VALUE;
    static int[][] S;
    static int[] left;
    static int[] right;
    static int[] up;
    static int[] down;

    static Pair adj(int here, int dir, int option) {
        switch (dir) {
        case 0:
            if (option == 0 && left[here] != -1) {
                return new Pair(left[here], Math.abs(S[here][1] - S[left[here]][1]));
            }
        case 1:
            if (option == 0 && right[here] != -1) {
                return new Pair(right[here], Math.abs(S[here][1] - S[right[here]][1]));
            }
        case 2:
            if (option == 1 && up[here] != -1) {
                return new Pair(up[here], Math.abs(S[here][0] - S[up[here]][0]));
            }
        case 3:
            if (option == 1 && down[here] != -1) {
                return new Pair(down[here], Math.abs(S[here][0] - S[down[here]][0]));
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        S = new int[K + 1][2];
        ArrayList<ArrayList<Pair>> R = new ArrayList<>();
        for (int i = 0; i <= N; i++) R.add(new ArrayList<>());
        ArrayList<ArrayList<Pair>> C = new ArrayList<>();
        for (int i = 0; i <= M; i++) C.add(new ArrayList<>());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            S[i][0] = y; S[i][1] = x;
            R.get(y).add(new Pair(i, x));
            C.get(x).add(new Pair(i, y));
        }
        // 도착지점 가상의 스위치
        S[K] = new int[] { N, M };
        R.get(N).add(new Pair(K, M));
        C.get(M).add(new Pair(K, N));
        for (int i = 0; i <= N; i++) Collections.sort(R.get(i));
        for (int j = 0; j <= M; j++) Collections.sort(C.get(j));
        left = new int[K + 1];
        right = new int[K + 1];
        up = new int[K + 1];
        down = new int[K + 1];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        Arrays.fill(up, -1);
        Arrays.fill(down, -1);
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < R.get(i).size(); j++) {
                int here = R.get(i).get(j).index;
                if (j - 1 >= 0) left[here] = R.get(i).get(j - 1).index;
                if (j + 1 < R.get(i).size()) right[here] = R.get(i).get(j + 1).index;
            }
        }
        for (int j = 1; j <= M; j++) {
            for (int i = 0; i < C.get(j).size(); i++) {
                int here = C.get(j).get(i).index;
                if (i - 1 >= 0) up[here] = C.get(j).get(i - 1).index;
                if (i + 1 < C.get(j).size()) down[here] = C.get(j).get(i + 1).index;
            }
        }
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        long[][] dist = new long[2][K + 1];
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        for (Pair p : R.get(1)) {
            pq.offer(new Tuple(p.index, p.value - 1, 0));
            dist[0][p.index] = p.value - 1;
        }
        while (!pq.isEmpty()) {
            Tuple t = pq.poll();
            int here = t.index; long cost = t.value; int dir = t.dir;
            int y = S[here][0]; int x = S[here][1];
            if (dist[dir][here] < cost) continue;
            // 스위치를 이용하는 경우
            if (dist[dir ^ 1][here] > cost + 1) {
                dist[dir ^ 1][here] = cost + 1;
                pq.offer(new Tuple(here, cost + 1, dir ^ 1));
            }
            // 다른 스위치로 이동하는 경우
            for (int d = 0; d < 4; d++) if (adj(here, d, dir) != null) {
                Pair e = adj(here, d, dir);
                int there = e.index; long thereCost = cost + e.value;
                if (dist[dir][there] > thereCost) {
                    dist[dir][there] = thereCost;
                    pq.offer(new Tuple(there, thereCost, dir));
                }
            }
        }
        long min = Math.min(dist[0][K], dist[1][K]);
        bw.append(min == INF ? -1 : min);
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) {
        return value - o.value;
    }
}
class Tuple implements Comparable<Tuple> {
    int index; long value; int dir;

    Tuple(int i, long v, int d) {
        index = i; value = v; dir = d;
    }

    @Override
    public int compareTo(Tuple o) {
        return Long.compare(value, o.value);
    }
}
