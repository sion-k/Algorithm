import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			String[] S = new String[N - 2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < S.length; i++) S[i] = st.nextToken();
			int cnt = 1;
			bw.append(S[0].charAt(0));
			for (int i = 0; i < S.length; i++) {
				if (i + 1 < S.length && S[i].charAt(1) != S[i + 1].charAt(0)) {
					bw.append(S[i].charAt(1)).append(S[i + 1].charAt(0));
					cnt--;
				} else {
					bw.append(S[i].charAt(1));
				}
			}
			if (cnt != 0) {
				bw.append("a");
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}