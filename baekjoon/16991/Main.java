import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static double[][] adj;
	static double[][][] cache;
	static final int INF = 16000000;

	static double dp(int start, int here, int visit) {
		visit |= (1 << here);
		if (Integer.bitCount(visit) == N) {
			return adj[here][start] == 0 ? INF : adj[here][start];
		}
		if (cache[start][here][visit] != 0) {return cache[start][here][visit];}
		double min = INF;
		for (int there = 0; there < N; there++) {
			if (adj[here][there] != 0 && (visit & (1 << there)) == 0) {
				min = Math.min(min, adj[here][there] + dp(start, there, visit));
			}
		}
		return cache[start][here][visit] = min;
	}

	static double dist(int y1, int x1, int y2, int x2) {
		return Math.sqrt(Math.pow((y2 - y1), 2) +
				Math.pow((x2 - x1), 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); adj = new double[N][N];
		int[][] pos = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				adj[i][j] = dist(pos[i][0], pos[i][1], pos[j][0], pos[j][1]);
			}
		}
		cache = new double[N][N][(int)(Math.pow(2, N))];
		double min = INF;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, dp(i, i, 0));
		}
		System.out.println(min);
	}

}
