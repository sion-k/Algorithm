package baekjoon.p09184;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][][] cache;

	static int dp(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0) return 1;
		if (a > 20 || b > 20 || c > 20) return dp(20, 20, 20);
		if (cache[a][b][c] != 0) return cache[a][b][c];
		int ret = 0;
		if (a < b && b < c)
			ret = dp(a, b, c - 1) + dp(a, b - 1, c - 1) - dp(a, b - 1, c);
		else
			ret = dp(a - 1, b, c) + dp(a - 1, b - 1, c) + dp(a - 1, b, c - 1) - dp(a - 1, b - 1, c - 1);
		return cache[a][b][c] = ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = "";
		cache = new int[21][21][21];
		while (!(line = br.readLine()).equals("-1 -1 -1")) {
			StringTokenizer st = new StringTokenizer(line, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			bw.write("w(" + a + ", " + b + ", " + c + ") = " + dp(a, b, c));
			bw.newLine();
		}
		bw.close();
	}

}