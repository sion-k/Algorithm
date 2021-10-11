import java.util.*;
import java.io.*;

public class Main {
    static long[][][] cache;

    static long dp(int n, int l, int r) {
        if (n == 1) return l == 1 && r == 1 ? 1 : 0;
        if (l == 1 && r == 1) return n == 1 ? 1 : 0;
        if (cache[n][l][r] != -1) return cache[n][l][r];
        long sum = 0;
        if (n - 1 >= l && n - 1 >= r) sum += (n - 2) * dp(n - 1, l, r);
        if (l - 1 >= 1) sum += dp(n - 1, l - 1, r);
        if (r - 1 >= 1) sum += dp(n - 1, l, r - 1);
        return cache[n][l][r] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        cache = new long[21][21][21];
        for (int i = 0; i < 21; i++)
            for (int j = 0; j < 21; j++)
                Arrays.fill(cache[i][j], -1);
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            bw.append(dp(n, l, r)).append("\n");
        }
        System.out.print(bw);
    }

}
