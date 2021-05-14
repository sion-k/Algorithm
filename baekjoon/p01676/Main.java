package baekjoon.p01676;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		BigInteger[] dp = new BigInteger[n + 1];
		dp[0] = BigInteger.ONE;
		for (int i = 1; i <= n; i++)
			dp[i] = dp[i - 1].multiply(new BigInteger(String.valueOf(i)));
		String str = dp[n].toString();
		int cnt = 0;
		for (int i = str.length() - 1; i >= 0; i--)
			if (str.charAt(i) == '0') cnt++;
			else break;
		System.out.println(cnt);
	}

}