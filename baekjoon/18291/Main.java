import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1000000007;
	
	// 2의 n승 반환
	static long pow(int n) {
		if (n == 0) return 1;
		if (n % 2 == 0) {
			long ret = pow(n / 2);
			return ret * ret % MOD;
		} else {
			return 2 * pow(n - 1) % MOD;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int ret = 0;
			if (N <= 2) {
				ret = 1;
			} else {
				N -= 2;
				ret = (int)pow(N);
			}
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}
