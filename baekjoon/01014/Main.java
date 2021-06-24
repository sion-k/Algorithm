import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] MAP; // 놓을 수 있는지 여부
	static int[][] cache;
	
	// i - 1번째 행에 놓인 학생들의 정보가 f일때, i번째 행부터 놓을 수 있는 학생의 최대 수
	static int dp(int i, int f) {
		if (i == N) return 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, 0);
	}
	
	// i번째 행, j번째 열부터 학생을 놓을 때 놓을 수 있는 최대 수
	// i - 1번째 행의 정보 f, 그리고 재귀 호출시에 넘겨줄 현재 행의 정보 nf
	static int btk(int i, int j, int f, int nf) {
		if (j >= M) return dp(i + 1, nf);
		int max = btk(i, j + 1, f, nf); // 놓지 않는 경우
		if (MAP[i][j]) {
			// 양 끝 예외 처리
			if (j == 0) {if ((f & (1 << (j + 1))) == 0) max = Math.max(max, 1 + btk(i, j + 2, f, nf | (1 << j)));}
			else if (j == M - 1) {if ((f & (1 << (j - 1))) == 0) max = Math.max(max, 1 + btk(i, j + 2, f, nf | (1 << j)));}
			// 일반적인 경우
			else if ((f & (1 << (j - 1))) == 0 && (f & (1 << (j + 1))) == 0) {max = Math.max(max, 1 + btk(i, j + 2, f, nf | (1 << j)));}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			MAP = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String row = br.readLine();
				for (int j = 0; j < M; j++) MAP[i][j] = (row.charAt(j) == '.'); 
			}
			cache = new int[N][(int)(Math.pow(2, M))];
			for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
			sb.append(dp(0, 0)).append("\n");
		}
		System.out.print(sb);
	}
	
}
