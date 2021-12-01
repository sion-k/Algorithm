import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Pair>> adj;
    static int removed = 0;

    // here에서 가장 멀리 떨어진 정점과 그곳까지의 거리 반환
    static Pair antipode(int here, int prev) {
        Pair ret = new Pair(here, 0);
        for (Pair e : adj.get(here)) {
            int there = e.index; int cost = e.value;
            if (there == prev || there == removed) continue;
            Pair cand = antipode(there, here);
            if (ret.value < cost + cand.value) {
                ret.index = cand.index;
                ret.value = cost + cand.value;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>(N);
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }
        int u = antipode(1, 1).index;
        Pair diameter = antipode(u, u);
        int v = diameter.index;
        removed = v;
        int max = antipode(u, u).value;
        removed = u;
        max = Math.max(max, antipode(v, v).value);
        bw.append(max);
        System.out.println(bw);
    }

}
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }
}
