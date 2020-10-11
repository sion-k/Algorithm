package baekjoon.p10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		br.close();
		int sum = 0;
		int overlapped = 0;
		for (int i = 0; i < S.length() - 1; i++) {
			if (S.charAt(i) == '(' && S.charAt(i + 1) == ')') {
				sum += overlapped; i++;
			} else if (S.charAt(i) == '(') {overlapped++; sum++;}
			else {overlapped--;}
		}
		System.out.println(sum);
	}
	
}