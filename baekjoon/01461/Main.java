import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> S1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> S2 = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x > 0) S1.add(x);
            else S2.add(-x);
        }
        int sum = -Math.max(S1.isEmpty() ? 0 : S1.peek(), S2.isEmpty() ? 0 : S2.peek());
        int size = 0;
        while (!S1.isEmpty()) {
            int x = S1.poll();
            if (size == 0) {
                size = M;
                sum += 2 * x;
            }
            size--;
        }
        size = 0;
        while (!S2.isEmpty()) {
            int x = S2.poll();
            if (size == 0) {
                size = M;
                sum += 2 * x;
            }
            size--;
        }
        System.out.println(sum);
    }

}
