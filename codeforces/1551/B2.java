import java.io.*;
import java.util.*;

public class B2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] S = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			int max = 0;
			for (int x : S) max = Math.max(max, x);
			int[] C = new int[max + 1];
			for (int x : S) C[x]++;
			int notUsed = 0;
			int[] t = new int[max + 1];
			for (int i = 1; i <= max; i++)
				if (C[i] >= K) {
					t[i] = 1;
				} else notUsed += C[i];
			int toAdd = (notUsed / K) * K;
			int added = 0; int r = 1;
			int[] ret = new int[N];
			for (int i = 0; i < N; i++)
				if (1 <= t[S[i]] && t[S[i]] <= K) ret[i] = t[S[i]]++;
				else if (t[S[i]] == 0 && added < toAdd) {
					ret[i] = r;
					r = (r + 1 > K ? 1 : r + 1);
					added++;
				}
			for (int x : ret) bw.append(x).append(" ");
			bw.append("\n");
		}	
		System.out.print(bw);
	}
	
}
