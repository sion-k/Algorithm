package baekjoon.p20154;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int[] d = { 3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int sum = 0;
		for (int i = 0; i < S.length(); i++) {
			sum = (sum + d[S.charAt(i) - 'A']) % 10;
		}
		System.out.println(sum % 2 != 0 ? "I'm a winner!" : "You're the winner?");
	}

}