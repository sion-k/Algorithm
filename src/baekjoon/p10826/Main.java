package baekjoon.p10826;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		BigInteger[] cache = new BigInteger[N + 1];
		Arrays.fill(cache, BigInteger.ZERO);
		if (N > 0) {cache[1] = BigInteger.ONE;}
		for (int i = 2; i <= N; i++) {
			cache[i] = cache[i].add(cache[i - 1].add(cache[i - 2]));
		}
		bw.write(cache[N].toString());
		bw.close();
	}

}