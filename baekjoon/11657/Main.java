import java.util.*;
import java.io.*;

public class Main {
	static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			adj.get(A).add(new Pair(B, C));
		}
		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		boolean flag = false;
		for (int k = 0; k < N; k++) {
			for (int here = 1; here <= N; here++) {
				for (Pair e : adj.get(here)) {
					int there = e.index; int cost = e.value;
					if (dist[here] != INF && dist[there] > dist[here] + cost) {
						dist[there] = dist[here] + cost;
						if (k == N - 1) flag = true;
					}
				}
			}
		}
		if (flag) {
			bw.append("-1\n");
		} else {
			for (int here = 2; here <= N; here++)
				bw.append(dist[here] == INF ? -1 : dist[here]).append("\n");
		}
		System.out.print(bw);
	}

}
class Pair implements Comparable<Pair> {
	int index, value;

	Pair (int i, int v) { index = i; value = v; }

	@Override
	public int compareTo(Pair o) { return value - o.value; }
}
