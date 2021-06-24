import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static ArrayList<ArrayList<Pair>> adj;
	static ArrayList<PriorityQueue<Integer>> dist;

	static ArrayList<PriorityQueue<Integer>> dijkstra(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		dist.get(src).offer(0);

		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;

			for (Pair edge : adj.get(here)) {
				int there = edge.num; int nextDist = cost + edge.cost;
				if (dist.get(there).size() < K) {
					pq.offer(new Pair(there, nextDist));
					dist.get(there).offer(-nextDist);
				} else if (-dist.get(there).peek() > nextDist) {
					pq.offer(new Pair(there, nextDist));
					dist.get(there).poll();
					dist.get(there).offer(-nextDist);
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Pair>>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {adj.add(new ArrayList<>());}

		dist = new ArrayList<>(N + 1);
		dist.add(new PriorityQueue<>());
		for (int i = 1; i <= N; i++) {dist.add(new PriorityQueue<>());}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(start).add(new Pair(end, weight));
		}

		ArrayList<PriorityQueue<Integer>> dist = dijkstra(1);
		for (int i = 1; i <= N; i++) {
			int cost = 0;
			if (dist.get(i).size() >= K) {
				cost = -dist.get(i).poll();
			} else {
				cost = -1;
			}
			bw.write(String.valueOf(cost));
			bw.newLine();
		}
		bw.close();
	}

}

class Pair implements Comparable<Pair> {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}
