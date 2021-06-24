package baekjoon.p20164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String S;
	
	// s에 대해서 연산
	static int dcMax(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++)
			if ((s.charAt(i) - '0') % 2 == 1) sum++;
		if (s.length() == 1) return sum;
		if (s.length() == 2) return sum + dcMax(String.valueOf((s.charAt(0) - '0') + (s.charAt(1) - '0')));
		int max = 0;
		// 세자리 이상이면 임의의 서로다른 두 군데에서 끊는다
		for (int i = 0; i < s.length() - 1; i++) {
			for (int j = i + 1; j < s.length() - 1; j++){
				int temp = Integer.parseInt(s.substring(0, i + 1));
				temp += Integer.parseInt(s.substring(i + 1, j + 1));
				temp += Integer.parseInt(s.substring(j + 1));
				int ret = dcMax(String.valueOf(temp));
				max = Math.max(max, ret);
			}
		}
		return sum + max;
	}
	
	// s에 대해서 연산
	static int dcMin(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++)
			if ((s.charAt(i) - '0') % 2 == 1) sum++;
		if (s.length() == 1) return sum;
		if (s.length() == 2) return sum + dcMin(String.valueOf((s.charAt(0) - '0') + (s.charAt(1) - '0')));
		int min = Integer.MAX_VALUE;
		// 세자리 이상이면 임의의 서로다른 두 군데에서 끊는다
		for (int i = 0; i < s.length() - 1; i++) {
			for (int j = i + 1; j < s.length() - 1; j++){
				int temp = Integer.parseInt(s.substring(0, i + 1));
				temp += Integer.parseInt(s.substring(i + 1, j + 1));
				temp += Integer.parseInt(s.substring(j + 1));
				int ret = dcMin(String.valueOf(temp));
				min = Math.min(min, ret);
			}
		}
		return sum + min;
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		System.out.println(dcMin(S) + " " + dcMax(S));
	}
	
}