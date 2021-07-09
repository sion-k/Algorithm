import java.io.*;
import java.util.*;

public class D {
	
	static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	// 1과 자기 자신을 제외한 약수의 개수 반환
	static int fac(int a) {
		int ret = 0;
		int range = (int)(Math.sqrt(a));
		for (int i = 2; i <= range; i++)
			while (a % i == 0) {
				a /= i;
				ret++;
			}
		return ret == 0 ? 1 : ret;
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
			int af = fac(a); int bf = fac(b); int gf = fac(gcd(a, b));
			int min = (af - gf > 0 ? 1 : 0) + (bf - gf > 0 ? 1 : 0);
			int mid = (a == b ? min : af + bf);
			int max = (a == 1 && b == 1 ? 0 : mid + 2 * gf);
			// System.out.printf("min : %d, mid : %d + %d = %d, max : %d + 2 * %d = %d\n", min, af, bf, mid, mid, gf, max);
			bw.append((min <= k && k <= mid || (mid < k && k <= max && (k - mid) % 2 == 0)) ? "YES" : "NO").append("\n");
		}
		System.out.print(bw);
	}

}