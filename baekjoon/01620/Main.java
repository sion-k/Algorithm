import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] S1 = new String[N + 1];
        Map<String, Integer> S2 = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            S1[i] = br.readLine();
            S2.put(S1[i], i);
        }
        for (int i = 0; i < M; i++) {
            String x = br.readLine();
            try {
                int num = Integer.parseInt(x);
                bw.append(S1[num]);
            } catch (Exception e) {
                bw.append(S2.get(x));
            }
            bw.append("\n");
        }
        System.out.print(bw);
    }

}
