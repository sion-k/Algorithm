package baekjoon.p02011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static String CIPHER;
	static int[] cache;
	// 암호를 i번째 인덱스부터 끝까지 해석했을 때 나올 수 있는 해석의 수
	static int dp(int i) {
		if (i == CIPHER.length()) {return 1;}
		if (cache[i] != -1) {return cache[i];}
		int sum = 0;
		int piece1 = CIPHER.charAt(i) - '0';
		if (piece1 != 0) {sum = (sum + dp(i + 1)) % 1000000;}
		else {return cache[i] = sum;}
		if (i <= CIPHER.length() - 2) {
			int piece2 = piece1 * 10 + CIPHER.charAt(i + 1) - '0';
			if (piece2 <= 26) {sum = (sum + dp(i + 2)) % 1000000;}
		}
		return cache[i] = sum;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		CIPHER = br.readLine();
		br.close();
		cache = new int[CIPHER.length()]; Arrays.fill(cache, -1);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(dp(0))); bw.newLine(); bw.close();
	}

}