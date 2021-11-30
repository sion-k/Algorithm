import java.util.*;
import java.io.*;

public class Main {
    static int[][] S;
    static int[][] cache;

    static final int NINF = -987654321;

    static int dp(int i, int b) {
        if (i == 11) return b == 0 ? 0 : NINF;
        if (cache[i][b] != -1) return cache[i][b];
        int max = NINF;
        for (int j = 0; j < 11; j++) if ((b & (1 << j)) > 0 && S[i][j] > 0) {
            max = Math.max(max, S[i][j] + dp(i + 1, b - (b & (1 << j))));
        }
        return cache[i][b] = max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            S = new int[11][11];
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 11; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            cache = new int[11][1 << 11];
            for (int i = 0; i < 11; i++) Arrays.fill(cache[i], -1);
            bw.append(dp(0, (1 << 11) - 1)).append("\n");
        }
        System.out.print(bw);
    }

}
