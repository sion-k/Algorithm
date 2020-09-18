package baekjoon.p1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static char[][] EXP = new char[10][8];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			
		}
		
		for (char[] e : EXP) {
			System.out.println(Arrays.toString(e));
		}
	}

}
