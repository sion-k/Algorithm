import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static boolean[] S;
    static int[] dist;
    static ArrayList<ArrayList<Integer>> adj;

    static void init() {
        Queue<Integer> q = new LinkedList<>();
        dist = new int[N + 1];
        Arrays.fill(dist, -1);
        for (int start = 1; start <= N; start++) {
            if (!S[start]) {
                q.offer(start);
                dist[start] = 0;
            }
        }
        while (!q.isEmpty()) {
            int here = q.poll();
            for (int there : adj.get(here)) if (dist[there] == -1) {
                dist[there] = dist[here] + 1;
                q.offer(there);
            }
        }
    }

    // x세기 폭탄으로 가능한지 여부
    static boolean f(int x) {
        Queue<Integer> q = new LinkedList<>();
        int[] booked = new int[N + 1];
        Arrays.fill(booked, -1);
        // 설치 가능한 지점에 모두 설치하고 폭파한다.
        for (int start = 1; start <= N; start++) {
            if (dist[start] >= x) {
                q.offer(start);
                booked[start] = 0;
            }
        }
        while (!q.isEmpty()) {
            int here = q.poll();
            if (booked[here] == x - 1) continue;
            for (int there : adj.get(here)) if (booked[there] == -1) {
                booked[there] = booked[here] + 1;
                q.offer(there);
            }
        }
        for (int here = 1; here <= N; here++) {
            if (S[here] && booked[here] == -1) return false;
            if (!S[here] && booked[here] != -1) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        S = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) S[i] = st.nextToken().equals("1");
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        init();
        int lo = 1; int hi = N;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (f(mid)) lo = mid;
            else hi = mid;
        }
        bw.append(lo);
        System.out.println(bw);
    }

}
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }
}
