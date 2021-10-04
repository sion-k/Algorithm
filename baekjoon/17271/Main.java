import java.util.*;
import java.io.*;

public class Main {
    static int M;

    static int[] cache;

    static int dp(int i) {
        if (i == 0) return 1;
        if (cache[i] != 0) return cache[i];
        int sum = dp(i - 1);
        if (i - M >= 0) sum += dp(i - M);
        if (sum >= MOD) sum -= MOD;
        return cache[i] = sum;
    }

    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cache = new int[N + 1];
        System.out.println(dp(N));
    }

}
