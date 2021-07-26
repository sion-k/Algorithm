import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			String S = br.readLine();
			int ret = 0;
			if (B >= 0) {
				ret = N * (A + B);
			} else {
				int c0 = S.charAt(0) == '0' ? 1 : 0;
				int c1 = S.charAt(0) == '1' ? 1 : 0;
				for (int i = 0; i < N - 1; i++) {
					if (S.charAt(i) == '1' && S.charAt(i + 1) == '0')
						c0++;
					if (S.charAt(i) == '0' && S.charAt(i + 1) == '1')
						c1++;
				}
				ret = N * A + (Math.min(c0, c1) + 1) * B;
			}
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}