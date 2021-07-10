package codeforce.r1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		int[] d = {1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111, 1111111111};
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int cnt = 0; int i = 1;
			while (i <= N) {
				if (i % 10 == 9) i = d[String.valueOf(i).length()];
				else i += d[String.valueOf(i).length() - 1];
				cnt++;
			}
			ans.append(cnt).append("\n");
		}
		System.out.print(ans);
	}
	
}