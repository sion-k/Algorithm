package baekjoon.p14389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] MAP; // i번째 열의 초기 상태를 저장
	static int[][] cache;
	
	// i번째 열의 상태가 f이고, 여기부터 블럭을 놓을 때 얻을 수 있는 최대 점수
	static int dp(int i, int f) {
		if (i == M) return 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, i == M - 1 ? 0 : MAP[i + 1]);
	}
	
	// i번째 열을 j번째 행부터 완전히 채우는 경우를 모두 시도한다
	// 단 현재 이미 채워져 있는 칸은 p로 나타내고
	// 모두 채운경우 dp(i + 1, f)를 통해 답을 구해서 반환한다
	// *4블록을 설치할 수 있으면 무조건 설치하는것이 최적해가 아님
	// 1..
	// ...
	// ...
	// 1..
	static int btk(int i, int j, int p, int f) {
		if (j == N) return dp(i + 1, f);
		// 이미 채워져 있는 열일 경우 다음으로 넘어간다
		if ((p & (1 << j)) > 0) return btk(i, j + 1, p, f);
		// 1블록을 설치하는 경우
		int max = 1 + btk(i, j + 1, p, f);
		// 4블록도 설치 가능하면 설치하는 경우
		if (i + 1 < M && j + 1 < N && (p & (1 << (j + 1))) == 0 && (f & (1 << j)) == 0 && (f & (1 << (j + 1))) == 0) {
			f |= (1 << j); f |= (1 << (j + 1));
			max = Math.max(max, 16 + btk(i, j + 2, p, f));			
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean[][] temp = new boolean[N][M];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++)
				if (row.charAt(j) == '1') {sum++; temp[i][j] = true;}
		}
		MAP = new int[M];
		for (int j = 0; j < M; j++) {
			int f = 0;
			for (int i = 0; i < N; i++) if (temp[i][j]) f |= (1 << i);
			MAP[j] = f;
		}
		cache = new int[M][(int)(Math.pow(2, N))];
		for (int i = 0; i < M; i++) Arrays.fill(cache[i], -1);
		System.out.println(sum + dp(0, MAP[0]));
	}
	
}