import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Pair> S = new ArrayList<>(2 * N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            S.add(new Pair(l, h));
            S.add(new Pair(r, -h));
        }
        Collections.sort(S);
        Heap h = new Heap();
        int lastIndex = S.get(0).index;
        int lastValue = -1;
        int i = 0;
        while (i < S.size()) {
            while (i < S.size() && S.get(i).index == lastIndex) {
                int index = S.get(i).index;
                int value = S.get(i).value;
                if (value > 0) h.offer(value);
                else h.remove(-value);
                i++;
            }
            int value = h.peek();
            if (value != lastValue) {
                bw.append(String.format("%d %d ", lastIndex, value));
                lastValue = value;
            }
            if (i < S.size()) lastIndex = S.get(i).index;
        }
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;
    Pair (int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return index - o.index; }
}
class Heap {
    PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> del = new PriorityQueue<>(Collections.reverseOrder());
    
    void offer(int x) {
        max.offer(x);
    }
    
    int peek() {
        while (!max.isEmpty() && !del.isEmpty() && max.peek().equals(del.peek())) {
            max.poll(); del.poll();
        }
        return max.isEmpty() ? 0 : max.peek();
    }

    void remove(int x) {
        del.offer(x);
    }
}
