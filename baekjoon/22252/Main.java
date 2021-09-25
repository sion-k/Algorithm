import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, PriorityQueue<Integer>> S = new HashMap<>();
        long sum = 0;
        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int o = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            if (S.get(name) == null) S.put(name, new PriorityQueue<>(Collections.reverseOrder()));
            PriorityQueue<Integer> pq = S.get(name);
            if (o == 1) {
                int k = Integer.parseInt(st.nextToken());
                for (int i = 0; i < k; i++) pq.offer(Integer.parseInt(st.nextToken()));
            } else {
                int b = Math.min(pq.size(), Integer.parseInt(st.nextToken()));
                for (int i = 0; i < b; i++) sum += pq.poll();
            }
        }
        System.out.println(sum);
    }

}
