package baekjoon.p02602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] S;
	static char[] A; static char[] B;

	// (i, j, k)다리를 건너가는 방법의 수 반환
	static int dp(int i, int j, int k) {
		// 끝에 도착하면 방법을 하나 찾음
		if (i >= A.length) {return 1;}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
	}

}
