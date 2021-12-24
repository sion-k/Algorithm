import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Pair> S = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int g = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            S.add(new Pair(d, g));
        }
        Collections.sort(S, (Pair u, Pair v) -> u.index - v.index);
        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair u, Pair v) -> u.value - v.value);
        for (Pair x : S) {
            if (pq.size() < x.index) {
                pq.offer(x);
            } else if (pq.peek().value < x.value) {
                pq.poll();
                pq.offer(x);
            }
        }
        int ret = 0;
        for (Pair x : pq) ret += x.value;
        bw.append(ret);
        System.out.println(bw);
    }

}
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }

    @Override
    public String toString() {
        return String.format("(%d %d)", index, value);
    }
}
