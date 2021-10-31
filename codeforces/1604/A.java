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
			int t = 0;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (S[i] > i + 1 + t) {
					int x = (S[i] - (i + 1 + t));
					cnt += x;
					t += x;
				}
			}
			bw.append(cnt);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}