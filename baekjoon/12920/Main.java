import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Pair> S = new ArrayList<>();
	static int[][] cache;
	
	static int dp(int i, int capacity) {
		if (i == S.size()) return 0;
		if (cache[i][capacity] != -1) return cache[i][capacity];
		int max = dp(i + 1, capacity);
		if (S.get(i).V <= capacity)
			max = Math.max(max, S.get(i).C + dp(i + 1, capacity - S.get(i).V));
		return cache[i][capacity] = max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int j = 1;
			while (K - j >= 0) {
				S.add(new Pair(V * j, C * j));
				K -= j;
				j <<= 1;
			}
			S.add(new Pair(V * K, C * K));
		}
		cache = new int[S.size()][M + 1];
		for (int i = 0; i < S.size(); i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, M));
	}

}

class Pair {
	int V, C;
	public Pair(int v, int c) { V = v; C = c; }
}
