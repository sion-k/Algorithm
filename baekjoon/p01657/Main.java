package baekjoon.p01657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] S;
	static int[][] cache;
	
	static final int[][] price = 
		{{10, 8, 7, 5, 0, 1},
		 { 8, 6, 4, 3, 0, 1},
		 { 7, 4, 3, 2, 0, 1},
		 { 5, 3, 2, 2, 0, 1},
		 { 0, 0, 0, 0, 0, 0},
		 { 1, 1, 1, 1, 0, 0}};
	
	// 두 자표로 이루어진 두부의 가격을 반환
	static int getPrice(int y1, int x1, int y2, int x2) {
		char p1 = S[y1][x1]; char p2 = S[y2][x2];
		return price[p1 - 'A'][p2 - 'A'];
	}
	
	// i번째 행부터 두부를 잘라낼 때 얻을 수 있는 두부가격 합의 최대
	// 단 이미 사용된 위치는 f로 나타낸다
	static int dp(int i, int f) {
		if (i == N) return 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, 0);
	}
	
	// i번째 행 j번째 열부터 두부를 잘라나갈 때 얻을 수 있는 최대값
	// 현재 필드가 f고 재귀호출할때 넘겨줄 필드가 nf
	static int btk(int i, int j, int f, int nf) {
		if (j >= M) return dp(i + 1, nf);
		if ((f & (1 << j)) > 0) return btk(i, j + 1, f, nf); // 선택할 수 없는 경우
		int max = btk(i, j + 1, f, nf); // 선택하지 않는 경우
		// 세로로 자를 수 있다면 자르기
		if (i + 1 < N && getPrice(i, j, i + 1, j) != 0) 
			max = Math.max(max, getPrice(i, j, i + 1, j) + btk(i, j + 1, f, nf | (1 << j)));
		// 가로로 자를 수 있다면 자르기
		if (j + 1 < M && (f & (1 << (j + 1))) == 0 && getPrice(i, j, i, j + 1) != 0)
			max = Math.max(max, getPrice(i, j, i, j + 1) + btk(i, j + 2, f, nf));
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N =	Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new char[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = row.charAt(j);
		}
		cache = new int[N][(int)(Math.pow(2, M))];
		for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}

}