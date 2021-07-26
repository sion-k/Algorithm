import java.io.*;
import java.util.*;

public class D {
	
	static long[] dn(long n) {
		long range = n;
		Map<Long, Integer> m = new HashMap<>();
		for (long i = 2; i <= (long)(Math.sqrt(range)); i++)
			while (n % i == 0) {
				n /= i;
				m.put(i, m.getOrDefault(i, 0) + 1);
			}
		long max = 0; long cnt = 0;
		for (Long x : m.keySet()) {
			if (cnt < m.get(x)) {
				max = x;
				cnt = m.get(x);
			}
		}
		if (max == 0) return new long[] { n, 1 };
		return new long[] { max, cnt };
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			long N = Long.parseLong(br.readLine());
			long[] pair = dn(N);
			bw.append(pair[1]).append("\n");
			for (int i = 0; i < pair[1] - 1; i++)
				bw.append(pair[0]).append(" ");
			for (int i = 0; i < pair[1] - 1; i++)
				N /= pair[0];
			bw.append(N);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}