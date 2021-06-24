import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static String S; static int L;
	static int[] cache;

	static int[][] palin;

	static int isPalin(int i, int j) {
		if (i >= j) {return 1;}
		if (palin[i][j] != -1) {return palin[i][j];}
		if (S.charAt(i) == S.charAt(j) && isPalin(i + 1, j - 1) == 1) {
			return palin[i][j] = 1;
		}
		return palin[i][j] = 0;
	}

	static int dp(int i ) {
		if (i >= L) {return 0;}
		if (cache[i] != 0) {return cache[i];}
		int min = 2500;
		for (int j = i; j < L; j++) {
			if (isPalin(i, j) == 1) {min = Math.min(min, dp(j + 1));}
		}
		return cache[i] = 1 + min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine(); L = S.length();
		palin = new int[L][L];
		for (int i = 0; i < L; i++) {Arrays.fill(palin[i], -1);}
		cache = new int[L];
		System.out.println(dp(0));
	}

}
