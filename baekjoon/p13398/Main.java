package baekjoon.p13398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	static int[][][] cache;
	static final int NINF = -1000000001;

	// 수를 지울수 있거나 없을 때 r ? 1 : 0
	// 이전에 선택하였거나 안했을 때 p ? 1 : 0
	// [i, N) 부분 수열에서 얻을 수 있는 최대 연속합
	static int dp(int r, int p, int i) {
		if (i >= N) {return 0;}
		if (cache[r][p][i] != NINF) {return cache[r][p][i];}
		// r과 p와 관계없이 언제나 무조건 고를 수 있음
		int max = S[i] + dp(r, 1, i + 1);
		// 바로 이전에 안고른 경우 또 안고를 수도있다
		if (p == 0) {max = Math.max(max, dp(r, p, i + 1));}
		// 바로 이전에 골랐을 경우 연속적으로 골라야 하므로
		else {
			// 더이상 안고르고 끝내는 경우
			max = Math.max(max, 0);
			// 지울 수 있으면 현재 인덱스 지우고 계속 고르거나 안고르는 경우
			if (r == 1) {max = Math.max(max, dp(0, p, i + 1));}
		}
		return cache[r][p][i] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); S = new int[N];
		cache = new int[2][2][N];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				Arrays.fill(cache[i][j], NINF);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		// 수열에서 수는 무조건 하나 골라야 한다
		int max = NINF;
		// 모든 경우는 최소 한개를 고르므로 모든 원소에 대해 고른 경우를 계산
		for (int i = 0; i < N; i++) {max = Math.max(max, S[i] + dp(1, 1, i + 1));}
		System.out.println(max);
	}

}