import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sum = 0;
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            sum += S[i];
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int b = 1; b < (1 << N); b++) {
            int temp = 0;
            for (int i = 0; i < N; i++)
                if ((b & (1 << i)) > 0) temp += S[i];
            m.put(temp, m.getOrDefault(temp, 0) + 1);
        }
        bw.append(sum - m.keySet().size()).append("\n");
        System.out.print(bw);
    }

}
