import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			boolean[] S = new boolean[a + b + 1];
			int l = Math.max(a, b) - ((a + b) / 2 + (a + b) % 2);
			int cnt = 0;
			for (int i = 0; i <= Math.min(a, b); i++) {
				S[l + 2 * i] = true;
				cnt++;
				if ((a + b) % 2 == 1) {
					S[l + 1 + 2 * i] = true;
					cnt++;
				}
			}
			bw.append(cnt).append("\n");
			for (int i = 0; i < S.length; i++)
				if (S[i]) bw.append(i).append(" ");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}