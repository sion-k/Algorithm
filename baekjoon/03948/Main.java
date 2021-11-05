import java.util.*;
import java.io.*;

public class Main {
    static long[][][] cache;

    static long dp(int left, int right, int k) {
        if (left == 0 && right == 0) return 1;
        if (cache[left][right][k] != -1) return cache[left][right][k];
        long sum = 0;
        if (k == 0) { // right에서 고르는 경우
            for (int i = 1; i <= right; i++) {
                sum += dp(left + i - 1, right - i, 1);
            }
        } else { // left에서 고르는 경우
            for (int i = 1; i <= left; i++) {
                sum += dp(i - 1, right + (left - i), 0);
            }
        }
        return cache[left][right][k] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        cache = new long[21][21][2];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache.length; j++)
                Arrays.fill(cache[i][j], -1);
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long sum = 0;
            if (N == 1) {
                bw.append(1).append("\n");
                continue;
            }
            for (int i = 1; i <= N; i++) {
                sum += 2 * dp(i - 1, N - i, 0);
            }
            bw.append(sum);
            bw.append("\n");
        }
        System.out.print(bw);
    }

}
