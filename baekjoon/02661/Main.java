import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N; static int[] S;
	static boolean finish = false;
	static StringBuilder A = new StringBuilder();

	static void BTK(int picked) {
		if (finish) {return;}
		if (picked == N) {
			for (int c : S) {A.append(c);}
			finish = true; return;
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
		N = Integer.parseInt(br.readLine()); S = new int[N];
		BTK(0);
		System.out.println(A);
	}

}
