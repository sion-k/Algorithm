import java.util.*;
import java.util.LinkedList;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int start = Integer.parseInt(st.nextToken());
            q.offer(start);
            dist[start] = 0;
        }
        while (!q.isEmpty()) {
            int here = q.poll();
            for (int i = 0; (1 << i) <= N; i++) {
                int there = here ^ (1 << i);
                if (0 <= there && there <= N && dist[there] == -1) {
                    dist[there] = dist[here] + 1;
                    q.offer(there);
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= N; i++) max = Math.max(max, dist[i]);
        bw.append(max);
        System.out.print(bw);
    }

}
