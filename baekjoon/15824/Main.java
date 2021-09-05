import java.io.*;
import java.util.*;

public class Main {
	static long[] cache;
	static final int MOD = 1000000007;
	
	// 2^x 반환
	static long dp(int x) {
		if (x == 0) return 1;
		if (cache[x] != 0) return cache[x];
		if (x % 2 == 0) {
			long ret = dp(x / 2);
			return cache[x] = (ret * ret) % MOD;
		} else {
			return cache[x] = 2 * dp(x - 1) % MOD;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> S = new ArrayList<>(N);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S.add(Integer.parseInt(st.nextToken()));
		Collections.sort(S);
		cache = new long[N];
		long sum = 0;
		for (int i = 0; i < N; i++) {
			long l = dp(i); long r = dp(N - i - 1);
			sum += (S.get(i) % MOD) * l % MOD;
			sum -= (S.get(i) % MOD) * r % MOD;
			sum = (sum % MOD + MOD) % MOD;
		}
		System.out.println(sum);
	}

}
