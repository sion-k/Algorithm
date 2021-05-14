package codeforce.r1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			String S = br.readLine();
			int first = -1;
			int last = -1;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (S.charAt(i) == '*') {
					if (first == -1) first = i;
					last = i;
					cnt++;
				}
			}
			ans.append(last - first - cnt + 1).append("\n");
		}
		System.out.print(ans);
	}
	
}