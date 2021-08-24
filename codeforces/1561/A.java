import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());
			int ret = 0;
			for (int i = 1; i <= N + 1; i++) {
				boolean sorted = true;
				for (int j = 1; j <= N - 1; j++)
					if (S[j] > S[j + 1]) sorted = false;
				if (sorted) {
					ret = i - 1;
					break;
				}
				if (i % 2 == 1) {
					for (int j = 1; j <= N - 2; j += 2)
						if (S[j] > S[j + 1]) {
							int temp = S[j]; S[j] = S[j + 1]; S[j + 1] = temp;
						}
				} else {
					for (int j = 2; j <= N - 1; j += 2)
						if (S[j] > S[j + 1]) {
							int temp = S[j]; S[j] = S[j + 1]; S[j + 1] = temp;
						}
				}
			}
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}