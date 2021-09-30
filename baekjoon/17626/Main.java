import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= N; i++)
            for (int j = 1; i + j * j <= N; j++)
                dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
        System.out.println(dp[N]);
    }

}
