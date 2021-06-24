import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visit;
	static ArrayList<Integer> sorted = new ArrayList<>();

	static void DFS(int here) {
		visit[here] = true;
		for (int there : adj.get(here)) {
			if (!visit[there]) {DFS(there);}
		}
		sorted.add(here);
	}

	static void topologicalSort() {
		for (int i = 1; i <= N; i++) {
			if (!visit[i]) {DFS(i);}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(N);
		adj.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {adj.add(new ArrayList<>());}
		visit = new boolean[N + 1];

		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj.get(start).add(end);
		}
		topologicalSort();
		StringBuilder ans = new StringBuilder();
		for (int i = sorted.size() - 1; i >= 0; i--) {
			ans.append(sorted.get(i));
			ans.append(" ");
		}
		for (int i = 1; i <= N; i++) {
			if (!visit[i]) {
				ans.append(sorted.get(i));
				ans.append(" ");
			}
		}
		System.out.println(ans.toString().trim());
	}

}
