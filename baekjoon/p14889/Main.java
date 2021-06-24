package baekjoon.p14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] S;
	static int min = 987654321;

	// [here, )에서 시작해서 toPick개를 고르는 경우 모두 시도한 뒤
	// 두 팀간의 능력치 차이 최소값을 갱신
	static void btk(int here, int toPick, boolean[] picked) {
		if (here == N) {
			if (toPick != 0) return;
			// picked가 true인 경우 start팀이라 가정
			int start = 0; int link = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (picked[i] == picked[j]) {
						if (picked[i]) start += S[i][j];
						else link += S[i][j];
					}
			min = Math.min(min, Math.abs(start - link));
			return;
		}
		// 앞으로 전부 다 골라야 하는 경우
		if ((N - here) == toPick) {
			for (int i = here; i < N; i++) picked[i] = true;
			btk(N, 0, picked);
			for (int i = here; i < N; i++) picked[i] = false;
			return;
		}
		// 고르지 않는 경우
		btk(here + 1, toPick, picked);
		// 고르는 경우
		if (toPick > 0) {
			picked[here] = true;
			btk(here + 1, toPick - 1, picked);
			picked[here] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				S[i][j] = Integer.parseInt(st.nextToken());
		}
		btk(0, N / 2, new boolean[N]);
		System.out.println(min);
	}

}