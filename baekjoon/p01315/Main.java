package baekjoon.p01315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}

		// 이전에 깬 퀘스트를 무시하고 만약 (i, j)가 갑자기 된다면 깰 수 있는 퀘스트 수
		int[][] cnt = new int[1001][1001];
		// 초기 상태 (1, 1)에서 (i, j)에 도달 가능한지 여부 저장
		boolean[][] reachable = new boolean[1001][1001];
		reachable[1][1] = true;
		// 이전에 깬 퀘스트를 무시하고 만약 (i, j)가 갑자기 된다면 깰 수 있는 퀘스트를 다 깨고 (i, j)를 충족해준뒤 남은 포인트
		int[][] point = new int[1001][1001];

		// 모든 가능한 (i, j)에 대해서 확인해본다
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= 1000; j++) {
				// cnt[i][j]를 계산해본다
				int sum = 0;
				for (int q = 0; q < N; q++)
					if (S[q][0] <= i || S[q][1] <= j) {
						sum += S[q][2]; cnt[i][j]++;
					}
				// point[i][j]를 계산
				point[i][j] = sum - i - j + 2;
				// (i, j)가 실제로 도달 가능한 상태고, 남은 포인트도 있으면 이웃한 상태도 도달 가능
				if (reachable[i][j] && point[i][j] > 0) {
					if (i + 1 <= 1000) reachable[i + 1][j] = true;
					if (j + 1 <= 1000) reachable[i][j + 1] = true;
				}
			}
		}
		int max = 0;
		for (int i = 1; i <= 1000; i++)
			for (int j = 1; j <= 1000; j++)
				if (reachable[i][j])
					max = Math.max(max, cnt[i][j]);
		System.out.println(max);
	}

}