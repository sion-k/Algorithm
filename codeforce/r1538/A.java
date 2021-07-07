import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			int min = N; int max = 0;
			int p = 0; int q = 0;
			for (int i = 0; i < N; i++) {
				if (min > S[i]) { min = S[i]; p = i + 1; }
				if (max < S[i]) { max = S[i]; q = i + 1; }
			}
			if (p > q) { int temp = p; p = q; q = temp; }
			int ret = p + N - q + 1;
			ret = Math.min(ret, Math.min(q, N - p + 1));
			bw.append(ret).append("\n");
		}
		System.out.print(bw);
	}

}