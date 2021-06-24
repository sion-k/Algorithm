import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	static int[] cache;

	static int dp(int start) {
		if (start >= N) return 0;
		if (cache[start] != -1) return cache[start];
		int ret = 0; int min = 10000; int max = 0;
		for (int end = start; end < N; end++) {
			min = Math.min(min, S[end]); max = Math.max(max, S[end]);
			ret = Math.max(ret, max - min + dp(end + 1));
		}
		return cache[start] = ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		cache = new int[N]; Arrays.fill(cache, -1);
		System.out.println(dp(0));
	}

}
