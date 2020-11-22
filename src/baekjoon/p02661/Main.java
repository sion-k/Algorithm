package baekjoon.p02661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] S; static int[] min;

	static void BTK(int picked) {
		if (picked == N) {
			for (int i = 0; i < N; i++) {
				if (S[i] < min[i]) {min = S.clone(); break;}
				else if (S[i] > min[i]) {break;}
			}
			return;
		}
		for (int pick = 1; pick <= 3; pick++) {
			S[picked] = pick;
			boolean ok = true;
			for (int n = 1; 2 * n <= picked + 1; n++) {
				ok = false;
				for (int i = 0; i < n; i++) {
					if (S[picked - i] != S[picked - n - i]) {
						ok = true; break;
					}
				}
				if (!ok) {break;}
			}
			if (ok) {BTK(picked + 1);}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N]; min = new int[N]; Arrays.fill(min, 4);
		BTK(0);
		StringBuilder ans = new StringBuilder();
		for (int c : min) {ans.append(c);}
		System.out.println(ans);
	}

}
