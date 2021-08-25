import java.io.*;
import java.util.*;

public class Main {

	static int[] f(int N, int[] S) {
		int[] ret = new int[1000001];
		ret[0]++;
		for (int i = 1; i <= N; i++)
			for (int k = 1; k <= N - 1; k++)
				if (i + k - 1 > N) {
					ret[S[N] - S[i - 1] + S[i + k - 1 - N]]++;
				} else {
					ret[S[i + k - 1] - S[i - 1]]++;
				}
		ret[S[N]]++;
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] S1 = new int[N + 1]; int[] S2 = new int[M + 1];
		for (int i = 1; i <= N; i++) S1[i] = Integer.parseInt(br.readLine()) + S1[i - 1];
		for (int i = 1; i <= M; i++) S2[i] = Integer.parseInt(br.readLine()) + S2[i - 1];
		int[] R1 = f(N, S1); int[] R2 = f(M, S2);
		long ret = 0;
		for (int i = 0; i <= K; i++)
			ret += (long)R1[i] * R2[K - i];
		System.out.println(ret);
	}

}
