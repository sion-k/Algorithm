import java.util.*;
import java.io.*;

public class Main {
    static int[][][] cache;

    static int dp(int left, int right, int k) {
        if (left == 0 && right == 0) return 1;
        if (cache[k][left][right] != -1) return cache[k][left][right];
        int n = left + right;
        int sum = 0;
        if (k == 0) {
            for (int r = 0; r <= right - 1; r++) {
                sum = (sum + dp(n - r - 1, r, k ^ 1)) % MOD;
            }
        } else {
            for (int l = 0; l <= left - 1; l++) {
                sum = (sum + dp(l, n - l - 1, k ^ 1)) % MOD;
            }
        }
        return cache[k][left][right] = sum;
    }

    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        cache = new int[2][N][N];
        for (int i = 0; i < N; i++) Arrays.fill(cache[0][i], -1);    
        for (int i = 0; i < N; i++) Arrays.fill(cache[1][i], -1);    
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = (sum + dp(i, N - i - 1, 0)) % MOD;
        }
        if (N == 1) {
            bw.append(1);
        } else {
            bw.append((2 * sum) % MOD);
        }
        System.out.println(bw);
    }

}
