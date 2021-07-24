import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<ArrayList<Tuple>> adj;
	
	static final long INF = Long.MAX_VALUE;
	
	static long dijkstra() {
		PriorityQueue<Tuple> pq = new PriorityQueue<>();
		pq.offer(new Tuple(1, 1, 0L));
		long[][] dist = new long[N + 1][11];
		for (int i = 0; i < N + 1; i++)
			Arrays.fill(dist[i], INF);
		dist[1][1] = 0;
		while (!pq.isEmpty()) {
			Tuple t = pq.poll();
			int here = t.num; int speed = t.speed;
			long cost = t.cost;
			if (dist[here][speed] < cost) continue;
			for (Tuple edge : adj.get(here)) {
				for (int d = -1; d <= 1; d++) {
					int thereSpeed = speed + d;
					if (!(1 <= thereSpeed && thereSpeed <= edge.limit)) continue;
					int there = edge.num;
					long thereCost = cost + edge.cost / thereSpeed;
					if (dist[there][thereSpeed] > thereCost) {
						dist[there][thereSpeed] = thereCost;
						pq.offer(new Tuple(there, thereSpeed, thereCost));
					}
				}
			}
		}
		long min = INF;
		for (int i = 1; i <= 10; i++)
			min = Math.min(min, dist[N][i]);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(N);
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Tuple(b, 3628800L * l, k));
			adj.get(b).add(new Tuple(a, 3628800L * l, k));
		}
		System.out.printf("%.9f\n", dijkstra() / 3628800.);
	}

}

class Tuple implements Comparable<Tuple> {
	int num, speed, limit;
	long cost;
	
	Tuple(int n, long c, int l) {
		num = n; cost = c; limit = l;
	}
	
	Tuple(int n, int s, long c) {
		num = n; speed = s; cost = c;
	}
	
	@Override
	public int compareTo(Tuple o) { return Long.compare(cost, o.cost); }

}
