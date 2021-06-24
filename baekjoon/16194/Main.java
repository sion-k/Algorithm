import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] P;
	static int[] cache;
	static final int INF = 10000000;

	static int dp(int S) {
		if (S == 0) {return 0;}
		if (cache[S] != 0) {return cache[S];}
		int min = INF;
		for (int pick = 1; pick <= S; pick++) {
			min = Math.min(min, P[pick] + dp(S - pick));
		}
		return cache[S] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		P = new int[N + 1]; cache = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {P[i] = Integer.parseInt(st.nextToken());}
		System.out.println(dp(N));
	}
}
