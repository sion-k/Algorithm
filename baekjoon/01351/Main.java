import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static long N; static int P; static int Q;
	static Map<Long, Long> cache = new HashMap<>();

	static long dp(long i) {
		if (i == 0) return 1;
		long ret = cache.getOrDefault(i, 0L);
		if (ret != 0) return ret;
		cache.put(i, ret = dp(i / P) + dp(i / Q));
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		System.out.println(dp(N));
	}

}
