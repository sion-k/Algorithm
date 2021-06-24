import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] W;
	static int start;
	static boolean[] VISIT;
	static final int INF = 9000001;

	// 정점 here에서 dfs
	static int DFS(int here) {
		boolean allVisit = true;
		for (boolean v : VISIT) {if(!v) {allVisit = false;}}
		if (here == start) {return allVisit ? 0 : INF;}
		int min = INF;
		for (int next = 0; next < N; next++) {
			if(W[here][next] != 0 && (!VISIT[next] || next == start)) {
				VISIT[next] = true;
				min = Math.min(min, W[here][next] + DFS(next));
				VISIT[next] = false;
			}
		}
		return min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine()); W = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {W[i][j] = Integer.parseInt(st.nextToken());}
		}
		VISIT = new boolean[N];
		int min = INF;
		for (int here = 0; here < N; here++) {
			start = here;
			for (int there = 0; there < N; there++) {
				if (W[here][there] == 0) {continue;}
				VISIT[there] = true;
				min = Math.min(min, W[here][there] + DFS(there));
				VISIT[there] = false;
			}
		}
		bw.write(String.valueOf(min));
		bw.close();
	}

}
