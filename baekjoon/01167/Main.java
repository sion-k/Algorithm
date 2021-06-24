import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Pair>> adj;
	static boolean[] visit;

	static Pair DFS(int here) {
		visit[here] = true;
		Pair max = new Pair(here, 0);
		for (Pair p : adj.get(here)) {
			int there = p.num;
			if (!visit[there]) {
				Pair cand = DFS(there); cand.weight += p.weight;
				if (cand.weight > max.weight) {max = cand;}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList<>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}
		for (int i = 0; i < N; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = 0;
			while ((end = Integer.parseInt(st.nextToken())) != -1) {
				int weight = Integer.parseInt(st.nextToken());
				adj.get(start).add(new Pair(end, weight));
				adj.get(end).add(new Pair(start, weight));
			}
		}
		visit = new boolean[N + 1];
		int leaf = DFS(1).num;
		visit = new boolean[N + 1];
		System.out.println(DFS(leaf).weight);
	}

}

class Pair {
	int num; int weight;
	public Pair(int n, int w) {num = n; weight = w;}
}
