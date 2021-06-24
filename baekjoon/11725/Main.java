import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] parent;

	static void dfs(int here) {
		visit[here] = true;
		for (int there : adj.get(here))
			if (!visit[there]) {
				parent[there] = here;
				dfs(there);
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		visit = new boolean[N + 1];
		adj = new ArrayList<>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) adj.add(new ArrayList<>());
		parent = new int[N + 1];
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj.get(start).add(end);
			adj.get(end).add(start);
		}
		dfs(1);
		for (int i = 2; i <= N; i++) {
			bw.write(String.valueOf(parent[i]));
			bw.newLine();
		}
		bw.close();
	}

}
