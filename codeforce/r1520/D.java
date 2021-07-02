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
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long cnt = 0;
			for (int i = 1; i <= N; i++)
				if (Integer.parseInt(st.nextToken()) - i == 0)
					cnt++;
			ans.append((cnt - 1) * cnt / 2).append("\n");
		}
		System.out.print(ans);
	}
	
}
