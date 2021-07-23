import java.io.*;
import java.util.*;

public class C {
	static String S;
	static int[][][] cache;
	
	// i번째 경기에 각각 a, b득점일 때 가장 빨리 끝나는 패널티 라운드
	static int dp(int i, int a, int b) {
		if (i == 10) return 10;
		// a가 다 이기고 b가 다 질 때
		int[] score = new int[2];
		score[0] = a; score[1] = b;
		for (int j = i; j < 10; j++)
			if (j % 2 == 0) score[j % 2]++;
		if (score[0] < score[1]) return i;
		score = new int[2];
		score[0] = a; score[1] = b;
		// b가 다 이기고 a가 다 질 때
		for (int j = i; j < 10; j++)
			if (j % 2 == 1) score[j % 2]++;
		if (score[0] > score[1]) return i;
		if (cache[i][a][b] != -1) return cache[i][a][b];
		if (S.charAt(i) == '?') {
			if (i % 2 == 0) {
				return cache[i][a][b] = Math.min(dp(i + 1, a + 1, b), dp(i + 1, a, b));
			} else {
				return cache[i][a][b] = Math.min(dp(i + 1, a , b + 1), dp(i + 1, a, b));
			}
		}
		if (i % 2 == 0) {
			return cache[i][a][b] = dp(i + 1, a + S.charAt(i) - '0', b);
		} else {
			return cache[i][a][b] = dp(i + 1, a, b + S.charAt(i) - '0');
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			S = br.readLine();
			cache = new int[10][11][11];
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 11; j++)
					Arrays.fill(cache[i][j] , -1);
			bw.append(dp(0, 0, 0));
			bw.append("\n");
		}
		System.out.print(bw);
	}

}