import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int W;
	static int[][] P;
	static int[][] cache;

	// 마지막으로 경찰차1이 i번째 사건을, 경찰차가 j번째 사건을 처리했을 때,
	// 나머지 사건을 모두 처리하는데 두 경찰차의 이동하는 거리 합의 최소
	static int dp(int i, int j) {
		if (i == W - 1 || j == W - 1) {return 0;}
		if (cache[i][j] != -1) {return cache[i][j];}
		int next = Math.max(i, j) + 1;
		return cache[i][j] =
		Math.min(dist(i, next) + dp(next, j), dist(j, next) + dp(i, next));
	}

	static int dist(int i, int j) {
		int dy = Math.abs(P[i][0] - P[j][0]);
		int dx = Math.abs(P[i][1] - P[j][1]);
		return dy + dx;
	}

	static StringBuilder ans = new StringBuilder();

	static void reconstruct(int i, int j) {
		if (i == W - 1 || j == W - 1) {return;}
		int next = Math.max(i, j) + 1;
		if (dp(i, j) == dist(i, next) + dp(next, j)){
			ans.append("1\n"); reconstruct(next, j);
		} else {
			ans.append("2\n"); reconstruct(i, next);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		// 0번째 사건은 (1, 1) 1번째 사건은 (n, n)으로 임의로 2개 추가
		W = Integer.parseInt(br.readLine()) + 2;
		P = new int[W][2];
		P[0] = new int[] {1, 1}; P[1] = new int[] {N, N};

		cache = new int[W][W];
		for (int i = 0; i < W; i++) {Arrays.fill(cache[i], -1);}

		for (int i = 2; i < W; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			P[i][0] = y; P[i][1] = x;
		}

		bw.write(String.valueOf(dp(0, 1)));
		bw.newLine();
		reconstruct(0, 1);
		bw.write(ans.toString());
		bw.close();
	}

}
