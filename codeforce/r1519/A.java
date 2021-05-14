package codeforce.r1519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long r = Integer.parseInt(st.nextToken());
			long b = Integer.parseInt(st.nextToken());
			long d = Integer.parseInt(st.nextToken());
			if (r < b) {long temp = r; r = b; b = temp;}
			System.out.println(b * (d + 1) >= r ? "YES" : "NO");
		}
	}
	
}