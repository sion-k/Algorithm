import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static Pair[] S;
	
	static int[][] cache;
	
	static final int INF = 987654321;
	
	// i번째 물품부터 고르기 시작해서 v만큼의 가치를 확보하는데
	// 필요한 최소 비용
	static int dp(int i, int v) {
		if (i == N) return v != 0 ? INF : 0;
		if (cache[i][v] != -1) return cache[i][v];
		int min = dp(i + 1, v);
		if (v - S[i].value >= 0)
			min = Math.min(min, S[i].weight + dp(i + 1, v - S[i].value));
		return cache[i][v] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		S = new Pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			S[i] = new Pair(w, v);
		}
		cache = new int[N][1000 * N + 1]; 
		for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		int[] R = new int[M];
		for (int i = 0; i < M; i++) R[i] = Integer.parseInt(br.readLine());
		double max = 0; int maxIndex = 0;
		for (int i = 0; i < M; i++) {
			int maxValue = 0;
			for (int j = 0; j <= 1000 * N; j++)
				if (dp(0, j) <= R[i]) maxValue = j;
			double cand = (double)maxValue / R[i];
			if (max < cand) {
				max = cand;
				maxIndex = i + 1;
			}
		}
		System.out.println(maxIndex);
	}

}
class Pair {
	int weight, value;
	
	Pair (int w, int v) { weight = w; value = v; }
}
