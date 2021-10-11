import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            bw.append(N / 2 + 1).append("\n");
            PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> hi = new PriorityQueue<>();
            int p = 0;
            for (int i = 0; i < (N + 9) / 10; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int t = 0;
                while (st.hasMoreTokens()) {
                    int x = Integer.parseInt(st.nextToken());
                    if (lo.isEmpty() || hi.isEmpty()) hi.offer(x);
                    else if (x >= lo.peek()) {
                        hi.offer(x);
                    } else {
                        lo.offer(x);
                    }
                    if (lo.size() + 1 < hi.size()) {
                        lo.offer(hi.poll());
                    } else if (lo.size() + 1 > hi.size()) {
                        hi.offer(lo.poll());
                    }
                    if (t % 2 == 0) {
                        bw.append(hi.peek()).append(" ");
                        p++;
                    }
                    t++;
                }
                if (p == 10 || i == (N + 9) / 10 - 1) {
                    bw.append("\n");
                    p = 0;
                }
            }
        }
        System.out.print(bw);
    }

}
