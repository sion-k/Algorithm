package baekjoon.p01793;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BigInteger[] dp = new BigInteger[251];
		dp[0] = BigInteger.ONE; dp[1] = BigInteger.ONE;
		BigInteger two = new BigInteger("2");
		dp[2] = new BigInteger("3");
		for (int i = 3; i <= 250; i++) {
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(two));
		}
		String line = "";
		while ((line = br.readLine()) != null) {
			int N = Integer.parseInt(line);
			bw.write(String.valueOf(dp[N]));
			bw.newLine();
		}
		bw.close();
	}

}