package baekjoon.p01439;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int[] cnt = new int[2];
		for (int i = 0; i < S.length(); i++) {
			if (i == S.length() - 1 || S.charAt(i) != S.charAt(i + 1)) {
				cnt[S.charAt(i) - '0']++;
			}
		}
		System.out.println(Math.min(cnt[0], cnt[1]));
	}

}