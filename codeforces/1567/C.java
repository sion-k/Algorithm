import java.io.*;
import java.util.*;

public class C {
	static int[] S;
	static int[][] cache;
	
	static int dp(int i, int b) {
		if (i == -1) return b == 0 ? 1 : 0;
		if (cache[i][b] != -1) return cache[i][b];
		int sum = 0;
		for (int u = 0; u <= 9; u++) {
			for (int v = 0; v <= 9; v++) {
				int k = u + v + (b & 1);
				if (k % 10 != S[i]) continue;
				if (k >= 10) {
					sum += dp(i - 1, (b | (1 << 2)) >> 1);
				} else {
					sum += dp(i - 1, b >> 1);
				}
			}
		}
		return cache[i][b] = sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			char[] temp = br.readLine().toCharArray();
			S = new int[temp.length];
			for (int i = 0; i < temp.length; i++) S[i] = temp[i] - '0';
			cache = new int[S.length][1 << 3];
			for (int i = 0; i < S.length; i++)
				Arrays.fill(cache[i], -1);
			bw.append(dp(S.length - 1, 0) - 2);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}