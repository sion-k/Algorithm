package codeforce.r1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] S = new int[N];
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(S);
			int ret = 0;
			int last = S[N - 1]; int cnt = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (S[i] == last) cnt++;
				else {
					ret += cnt;
					last = S[i]; cnt = 1;
				}
			}
			ans.append(ret).append("\n");
		}
		System.out.print(ans);
	}
	
}
