import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			int[] C = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
				C[S[i]]++;
			}
			int cnt = 0;
			for (int i = 1; i <= N; i++)
				if (C[i] == 1) cnt++;
			if (cnt == 0) {
				bw.append(-1);
			} else {
				int min = N + 1; int index = N + 1;
				for (int i = 0; i < N; i++) if (C[S[i]] == 1) {
					if (min > S[i]) {
						min = S[i];
						index = i + 1;
					}
				}
				bw.append(index);
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}