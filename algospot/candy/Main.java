import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] S;
	static int[][][][] cache;
	static int MAX;
	
	static int dp(int i, int a, int b, int c) {
		int[] t = new int[] { a, b, c };
		Arrays.sort(t);
		a = t[0]; b = t[1]; c = t[2];
		if (i == N) return c - a;
		if (cache[i][a][b][c] != -1) return cache[i][a][b][c];
		int min = 20;
		if (a + S[i] < MAX) min = Math.min(min, dp(i + 1, a + S[i], b, c));
		if (b + S[i] < MAX) min = Math.min(min, dp(i + 1, a, b + S[i], c));
		if (c + S[i] < MAX) min = Math.min(min, dp(i + 1, a, b, c + S[i]));
		return cache[i][a][b][c] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		MAX = 20 * N / 3 + 20;
		cache = new int[N][MAX][MAX][MAX];
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < cache[i].length; j++)
				for (int k = 0; k < cache[i][j].length; k++)
					Arrays.fill(cache[i][j][k], -1);
		System.out.println(dp(0, 0, 0, 0));
	}

}
