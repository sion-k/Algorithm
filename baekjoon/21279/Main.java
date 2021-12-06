import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Pair>> P = new ArrayList<>(100000);
        for (int i = 0; i <= 100000; i++) P.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            P.get(x).add(new Pair(y, v));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        long max = 0; long sum = 0;
        for (int x = 0; x <= 100000; x++) {
            // X좌표가 x인 광물들을 모두 pq에 넣는다
            for (Pair p : P.get(x)) {
                pq.offer(p);
                sum += p.v;
            }
            int last = -1;
            // y좌표가 큰 것부터 pq에서 뺀다
            while (pq.size() > C) {
                sum -= pq.peek().v;
                last = pq.poll().y;
            }
            // 마지막으로 뺀 y좌표와 같은 광물을 pq에서 뺀다
            while (!pq.isEmpty() && pq.peek().y == last) {
                sum -= pq.poll().v;
            }
            max = Math.max(max, sum);
        }
        bw.append(max);
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int y, v;

    Pair(int y, int v) { this.y = y; this.v = v; }

    public int compareTo(Pair o) { return o.y - y; }
}
