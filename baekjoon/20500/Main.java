import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] cache;

    static final int MOD = 1000000007;

    static int dp(int i, int k) {
        if (i == N) return (k + 5) % 3 == 0 ? 1 : 0;
        if (cache[i][k] != -1) return cache[i][k];
        return cache[i][k] = (dp(i + 1, (k + 1) % 3) + dp(i + 1, (k + 5) % 3)) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N + 1][3];
        for (int i = 0; i < N + 1; i++) Arrays.fill(cache[i], -1);
        System.out.println(dp(1, 0));
    }

}
