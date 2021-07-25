import java.io.*;
import java.util.*;

public class Main {
	static char[] S;
	static int[][] cache;
	
	static final int MOD = 10007;
	
	static int dp(int i, int j) {
		if (i > j) return 0;
		if (cache[i][j] != -1) return cache[i][j];
		return cache[i][j] = (dp(i + 1, j) + dp(i, j - 1) - dp(i + 1, j - 1) + (S[i] == S[j] ? 1 + dp(i + 1, j - 1) : 0) + MOD) % MOD;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		cache = new int[S.length][S.length];
		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, S.length - 1));
	}

}
