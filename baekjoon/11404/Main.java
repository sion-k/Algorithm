import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long INF = 10000000000L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] adj = new long[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {Arrays.fill(adj[i], INF);}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[from][to] = Math.min(adj[from][to], weight);
		}
		for (int i = 1; i <= N; i++) {adj[i][i] = 0;}
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (adj[i][j] >= INF) {bw.write("0");}
				else {bw.write(String.valueOf(adj[i][j]));};
				if (j != N) {bw.write(" ");}
			}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}
