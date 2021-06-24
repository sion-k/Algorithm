import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static int[][] adj;
	static final int INF = 987654321;

	static void floyd() {
		for (int k = 1; k <= V; k++)
			for (int i = 1; i <= V; i++)
				for (int j = 1; j <= V; j++)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		adj = new int[V + 1][V + 1];
		for (int i = 0; i < V + 1; i++)
			Arrays.fill(adj[i], INF);
		int E = Integer.parseInt(st.nextToken());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u][v] = w;
		}
		floyd();
		int min = INF;
		for (int i = 1; i <= V; i++)
			min = Math.min(min, adj[i][i]);
		if (min == INF) min = -1;
		System.out.println(min);
	}

}
