package baekjoon.p02602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] S;
	static char[] A; static char[] B;

	// (i, j, k)�ٸ��� �ǳʰ��� ����� �� ��ȯ
	static int dp(int i, int j, int k) {
		// ���� �����ϸ� ����� �ϳ� ã��
		if (i >= A.length) {return 1;}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
	}

}
