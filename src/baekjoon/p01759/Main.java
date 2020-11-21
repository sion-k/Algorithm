package baekjoon.p01759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static String[] S; static String[] R;
	static boolean[] taken;
	static StringBuilder A = new StringBuilder();

	static final String[] VOWEL = {"a", "e", "i", "o", "u"};

	static void BTK(int lastPick, int picked) {
		if (picked == M) {
			for (int i = 0; i < M; i++) {A.append(R[i]).append(" ");}
			A.append("\n");
			return;
		}
		for (int i = lastPick + 1; i < N; i++) {
			if (!taken[i]) {
				R[picked] = S[i]; taken[i] = true;
				BTK(i, picked + 1);
				taken[i] = false;
			}
		}
	}

	static void preBTK(int lastPick, int picked, int vowel, int consonant) {
		System.out.println(Arrays.toString(R));
		if (vowel == 1 && consonant == 2) {
			BTK(lastPick, 3);
			return;
		}
		if (vowel == 0) {
			for (int i = lastPick + 1; i < N; i++) {
				boolean isVowel = false;
				for (int v = 0; v < 5; v++) {
					if (S[i].equals(VOWEL[v])) {isVowel = true;}
				}
				if (isVowel) {
					R[picked] = S[i]; taken[i] = true;
					preBTK(i, picked + 1, vowel + 1, consonant);
					taken[i] = false;
				}
			}
		} else if(consonant < 2) {
			for (int i = lastPick + 1; i < N; i++) {
				boolean isVowel = false;
				for (int v = 0; v < 5; v++) {
					if (S[i].equals(VOWEL[v])) {isVowel = true;}
				}
				if (!isVowel) {
					R[picked] = S[i]; taken[i] = true;
					preBTK(i, picked + 1, vowel, consonant + 1);
					taken[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		S = new String[N]; R = new String[M];
		taken = new boolean[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {S[i] = st.nextToken();}
		Arrays.sort(S);
		preBTK(-1, 0, 0, 0);
		System.out.print(A);
	}

}