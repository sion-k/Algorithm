import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger[] dp = new BigInteger[N + 1];
        dp[0] = new BigInteger("0");
        dp[1] = new BigInteger("0");
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1].multiply(BigInteger.TWO).add(BigInteger.ONE);
            if (i % 2 == 1) dp[i] = dp[i].subtract(BigInteger.ONE);
        }
        System.out.println(dp[N].subtract(dp[N - 1]));
    }

}
