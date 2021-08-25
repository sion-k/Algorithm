import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] S;
	static int[][] cache;
	
	static final int NULL = Integer.MIN_VALUE;
	
	static int dp(int a, int i) {
		if (i == N) return a == 1 ? 0 : NULL;
		if (cache[a][i] != NULL) return cache[a][i];
		if (a == 0) cache[a][i] = Math.max(dp(0, i + 1), dp(1, i));
		else cache[a][i] = Math.max(S[i], S[i] + dp(1, i + 1));
		return cache[a][i];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			N = Integer.parseInt(br.readLine());
			S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			cache = new int[2][N];
			for (int i = 0; i < 2; i++) Arrays.fill(cache[i], NULL);
			bw.append(dp(0, 0)).append("\n");
		}
		System.out.print(bw);
	}

}
