import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	static int[][] cache;

	// 마지막으로 i j번째 음을 불렀을 때 노래할 때 힘들 수 있는 정도의 최솟값
	static int dp(int i, int j) {
		if (i > j) {int temp = i; i = j; j = temp;}
		if (i == N|| j == N) {return 0;} // 다 부른 경우
		// 힘든 정도는 0일 수도 있음
		if (cache[i][j] != -1) {return cache[i][j];}
		// 다음으로 불러야 할 음
		int next = Math.max(i, j) + 1;
		int cost1 = 0;
		if (i != 0) cost1 = Math.abs(S[next] - S[i]);
		int cost2 = 0;
		if (j != 0) cost2 = Math.abs(S[next] - S[j]);
		return cache[i][j] =
		Math.min(cost1 + dp(next, j), cost2 + dp(i, next));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		cache = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}

}
