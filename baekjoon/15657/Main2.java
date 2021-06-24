import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	static int N; static int M;
	static int[] S; static int[] R;
	static StringBuilder A = new StringBuilder();

	static void BFC(int lastPick, int picked) {
		if (picked == M) {
			for (int i = 0; i < S.length; i++) {
				A.append(S[i]).append(" ");
			}
			A.append("\n");
			return;
		}
		for (int i = lastPick; i < N; i++) {
			S[picked] = R[i];
			BFC(i, picked + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[M]; R = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			R[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(R);
		BFC(0, 0);
		System.out.print(A);
	}

}
