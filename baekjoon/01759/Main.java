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
			int vowel = 0; int consonant = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < 5; j++) {
					if (R[i].equals(VOWEL[j])) {vowel++; break;}
				}
			}
			consonant = M - vowel;
			if (vowel >= 1 && consonant >= 2) {
				for (int i = 0; i < M; i++) {A.append(R[i]);}
				A.append("\n");
			}
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
		BTK(-1, 0);
		System.out.print(A);
	}

}
