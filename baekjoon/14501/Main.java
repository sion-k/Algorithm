import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] T; static int[] P;
	static int[] cache;

	// i번째 일 이후로 얻을 수 있는 최대 이득
	static int dp(int i) {
		if (i > N) {return 0;}
		if (cache[i] != -1) {return cache[i];}
		cache[i] = dp(i + 1);
		// 상담이 끝나는 날짜가 퇴사 전이라면
		if (i + T[i] - 1 <= N) {
			cache[i] = Math.max(cache[i], P[i] + dp(i + T[i]));
		}
		return cache[i];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N + 1]; Arrays.fill(cache, -1);
		T = new int[N + 1]; P = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dp(1));
	}

}
