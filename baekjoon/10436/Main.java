import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " |/");
			st.nextToken();
			long p = Long.parseLong(st.nextToken());
			long q = Long.parseLong(st.nextToken());
			if (p == 1 && q == 1) {
				p = 1; q = 2;
			} else if (q == 1) {
				q = p + 1; p = 1;
			} else if (p > q) {
				int cnt = 0;
				while (p > q) {
					p -= q;
					cnt++;
				}
				long t = p;
				p = q;
				q -= t;
				while (cnt-- > 0) {
					q += p;
				}
			} else {
				long t = p;
				p = q;
				q -= t;
			}
			bw.append(tc).append(" ").append(p).append("/").append(q).append("\n");
		}
		System.out.print(bw);
	}

}
