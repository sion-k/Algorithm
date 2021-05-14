package baekjoon.p01475;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int[] digit = new int[10];
		for (int i = 0; i < S.length(); i++) digit[S.charAt(i) - '0']++;
		int temp = digit[6] + digit[9];
		digit[6] = digit[9] = temp / 2;
		if (temp % 2 == 1) digit[6]++;
		int max = 0;
		for (int i = 0; i < 10; i++) max = Math.max(max, digit[i]);
		System.out.println(max);
	}

}