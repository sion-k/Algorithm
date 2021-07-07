import java.io.*;
import java.util.*;

public class D {
	
	static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int lo = 2;
			if (a != b && (a % b == 0 || b % a == 0)) lo = 1;
			int gcd = gcd(a, b); int cnt = 0;
			for (int i = 1; i <= (int)(Math.sqrt(gcd)); i++) {
				if (gcd % i == 0) cnt++;
			}
			int hi = lo + 2 * (cnt % 2 == 1 ? cnt / 2 + 1 : cnt / 2);
			boolean flag = lo <= k && k <= hi;
			if (a == b && k == 1) flag = false;
			bw.append(flag ? "YES" : "NO").append("\n");
		}
		System.out.print(bw);
	}

}