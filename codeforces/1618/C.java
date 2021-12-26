import java.io.*;
import java.util.*;

public class C {
	
	static long gcd(long a, long b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			long[] S = new long[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Long.parseLong(st.nextToken());
			long odd = S[1]; long even = S[0];
			for (int i = 0; i < N; i++) {
				if (i % 2 == 1) {
					odd = gcd(odd, S[i]);
				} else {
					even = gcd(even, S[i]);
				}
			}
			// bw.append(String.format("%d %d\n", odd, even));
			boolean flag = true;
			for (int i = 0; i < N; i += 2) {
				if (S[i] % odd == 0) flag = false;
			}
			if (flag) {
				bw.append(odd); bw.append("\n");
				continue;
			}
			flag = true;
			for (int i = 1; i < N; i += 2) {
				if (S[i] % even == 0) flag = false;
			}
			if (flag) {
				bw.append(even); bw.append("\n");
				continue;
			}
			bw.append("0");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}