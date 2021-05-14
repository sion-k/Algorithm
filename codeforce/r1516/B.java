package codeforce.r1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			boolean allSame = true;
			for (int i = 1; i < N; i++)
				if (S[i] != S[0]) allSame = false;
			int ret = 0;
			for (int i = 0; i < N; i++) ret ^= S[i];
			int cnt = 0;
			for (int i = 0; i < N; i++)
				if (ret == S[i]) cnt++;
			ans.append(allSame || cnt >= 2 ? "YES" : "NO").append("\n");
		}
		System.out.print(ans);
	}
	
}