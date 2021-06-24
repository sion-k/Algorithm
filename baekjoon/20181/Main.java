import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static long[] S;
	static long[] cache;
	
	// S[head, tail]중 합이 K를 넘는 가장 작은 tail 반환
	static int bin(int head) {
		int lo = head - 1; int hi = N + 1;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (S[mid] - S[head - 1] >= K) hi = mid;
			else lo = mid;
		}
		return hi;
	}
	
	// here부터 지나치거나 먹기 시작할 수 있을 때
	// 얻을 수 있는 최대 탈피 에너지
	static long dp(int here) {
		if (here >= N + 1) return 0;
		if (cache[here] != -1) return cache[here];
		long max = dp(here + 1); // 지나치는 경우
		// 먹기 시작하는 경우
		int tail = bin(here);
		if (tail != N + 1) {
			long sum = S[tail] - S[here - 1];
			max = Math.max(max, sum - K + dp(tail + 1));
		}
		return cache[here] = max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		S = new long[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
		cache = new long[N + 1];
		Arrays.fill(cache, -1);
		System.out.println(dp(1));
	}
	
}
