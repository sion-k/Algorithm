package codeforce.r1506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			long x = Long.parseLong(st.nextToken());
			long ret = (((x % n == 0) ? n : (x % n)) - 1) * (long)m + (x - (x % n == 0 ? n : x % n)) / (long)n + 1;
			System.out.println(ret);
		}
		System.out.print(ans.toString().trim());
	}

}