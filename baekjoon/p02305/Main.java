package baekjoon.p02305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, K;
	static int[][][] cache;
	
	// i - 1번째 좌석에 앉는 사람에 t번째 티켓을 산 사람이고 (0이면 비워두는 경우)
	// 지정석에는 f번째 티켓을 산 사람이 앉고(0이면 비워두는 경우)
	// [i, ...]까지 좌석을 채우는 경우의 수
	static int dp(int i, int t, int f) {
		if (i > N) return 1;
		if (cache[i][t][f] != -1) return cache[i][t][f];
		if (i == K) return dp(i + 1, t, f); // 지정석인 경우 이미 배치했으므로 넘어감
		int sum = 0;
		if (f == 0 || i != f) { // 지정석이 비었거나 지정석 간 사람 자리가 아니라면 원래 티켓을 산 사람이 앉아야 한다
			sum += dp(i + 1, i, f);
		} else { // 지정석에 누군가 앉았다면, 그리고 현재 위치가 그 사람의 자리라면 비워야한다
			sum += dp(i + 1, 0, f);
		}
		// 바로 이전 칸이 지정석이 아니고, 그 사람과 바꿀 수 있는 경우 바꿔본다
		if (i - 1 >= 1 && i - 1 != K && (i == t + 1 || t == 0)) 
			sum += dp(i + 1, t, f);
		return cache[i][t][f] = sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		cache = new int[N + 1][N + 2][N + 1];
		for (int i = 0; i < N + 1; i++)
			for (int j = 0; j < N + 2; j++)
				Arrays.fill(cache[i][j], -1);
		int sum = 0;
		for (int f = 0; f <= N; f++)
			if (f != K) sum += dp(1, N + 1, f);
		System.out.println(sum);
	}
	
}