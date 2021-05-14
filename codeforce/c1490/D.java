package codeforce.c1490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D {
	static int[] S; static int[] D;
	
	static void build(int l, int r, int d) {
		if (l > r) {return;}
		int max = 0; int maxIndex = 0;
		for (int i = l; i <= r; i++) if (max < S[i]) {max = S[i]; maxIndex = i;}
		D[maxIndex] = d;
		build(l, maxIndex - 1, d + 1);
		build(maxIndex + 1, r, d + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			S = new int[N]; D = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			build(0, N - 1, 0);
			StringBuilder ans = new StringBuilder();
			for (int i = 0; i < N; i++) ans.append(D[i]).append(" ");
			System.out.println(ans.toString().trim());
		}
	}

}