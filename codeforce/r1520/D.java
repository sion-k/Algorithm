package codeforce.r1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			long cnt = 0; int last = N - 1;
			for (int i = N - 2; i >= 0; i--) {
				if (last - i == S[last] - S[i]) {
					cnt++;
					last = i;
				}
			}
			ans.append(cnt * (cnt + 1) / 2).append("\n");
		}
		System.out.print(ans);
	}
	
}