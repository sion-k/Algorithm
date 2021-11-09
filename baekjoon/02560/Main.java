import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] P = new int[N + 1];
        P[0] = 1;
        for (int i = 1; i <= N; i++) {
            P[i] = P[i - 1];
            if (i - a >= 0) P[i] = (P[i] + P[i - a] + 1000) % 1000;
            if (i - b >= 0) P[i] = (P[i] - P[i - b] + 1000) % 1000;
        }
        int sum = P[N];
        if (N - d >= 0) sum = (sum - P[N - d] + 1000) % 1000;
        bw.append(sum).append("\n");
        System.out.print(bw);
    }

}
