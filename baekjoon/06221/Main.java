import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] S;

    static int[] cache;

    static int dp(int i) {
        if (cache[i] != -1) return cache[i];
        int max = 1;
        for (int j = 0; j < N; j++) {
            if (S[i][0] < S[j][0] && S[i][1] < S[j][1]) {
                max = Math.max(max, 1 + dp(j));
            }
        }
        return cache[i] = max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        S = new int[N][2];
        cache = new int[N];
        Arrays.fill(cache, -1);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            S[i][0] = Integer.parseInt(st.nextToken());
            S[i][1] = Integer.parseInt(st.nextToken());
        }
        int max = 1;
        for (int i = 0; i < N; i++) max = Math.max(max, dp(i));
        bw.append(max);
        System.out.println(bw);
    }

}
