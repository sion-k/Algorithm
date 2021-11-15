import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] S = new int[N];
        for (int i = 0; i < N; i++) S[i] = Integer.parseInt(br.readLine());
        Map<Integer, Integer> p = new HashMap<>();
        Map<Integer, Integer> np = new HashMap<>();
        p.put(S[0], 1);
        for (int i = 1; i < N; i++) np.put(S[i], np.getOrDefault(S[i], 0) + 1);
        int min = N + 1;
        int head = 0; int tail = 0;
        // [head, tail]
        while (head < N) {
            while (tail + 1 < N && p.keySet().size() < K) {
                p.put(S[tail + 1], p.getOrDefault(S[tail + 1], 0) + 1);
                np.put(S[tail + 1], np.get(S[tail + 1]) - 1);
                if (np.get(S[tail + 1]) == 0) np.remove(S[tail + 1]);
                tail++;
            }
            if (p.keySet().size() == K && np.keySet().size() == K) {
                min = Math.min(min, tail - head + 1);
            }
            p.put(S[head], p.get(S[head]) - 1);
            if (p.get(S[head]) == 0) p.remove(S[head]);
            np.put(S[head], np.getOrDefault(S[head], 0) + 1);
            head++;
        }
        System.out.println(min == N + 1 ? 0 : min);
    }
}