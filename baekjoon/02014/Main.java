import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] S = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) S[i] = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int x : S) pq.offer((long)x);
        while (!pq.isEmpty()) {
            long here = pq.poll();
            while (!pq.isEmpty() && pq.peek() == here) pq.poll();
            if (--N == 0) {
                bw.append(here);
                break;
            }
            for (int x : S) {
                long there = here * x;
                pq.offer(there);
                if (here % x == 0) break;
            }
        }
        System.out.println(bw);
    }

}
