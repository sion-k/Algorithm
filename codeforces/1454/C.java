import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N]; int[] C = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N - 1; i++)
				if (S[i] != S[i + 1]) C[S[i]]++;
			C[S[N - 1]]++;
			int min = N;
			for (int i = 1; i <= N; i++) if (C[i] != 0) {
				int cand = C[i] + 1;
				if (S[0] == i) cand--;
				if (S[N - 1] == i) cand--;
				min = Math.min(min, cand);
			}
			bw.append(min);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}