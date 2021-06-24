import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] adj;
	static boolean[] visit;
	
	// here이 포함된 연결 요소가 트리인지 반환
	static boolean dfs(int here, int prev) {
		visit[here] = true;
		for (int there = 1; there <= N; there++) {
			if (adj[here][there] && there != prev) {
				if (visit[there]) return false;
				if (!dfs(there, here)) return false;
			}
		}
		return true;
	}
	
	static int dfsAll() {
		visit = new boolean[N + 1];
		int cnt = 0;
		for (int here = 1; here <= N; here++)
			if (dfs(here, 0)) cnt++;
		return cnt;
	}
	
	static String parse(int n) {
		switch (n) {
		case 0 : return "No trees.";
		case 1 : return "There is one tree.";
		}
		return String.format("A forest of %d trees.", n);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		String line = ""; int TC = 1;
		while (!(line = br.readLine()).equals("0 0")) {
			StringTokenizer st = new StringTokenizer(line, " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			adj = new boolean[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(),  " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adj[u][v] = adj[v][u] = true;
			}
			ans.append(String.format("Case %d: ", TC++)).append(parse(dfsAll())).append("\n");
		}
		System.out.print(ans);
	}
	
}
