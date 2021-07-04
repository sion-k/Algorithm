import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<ArrayList<Tuple>> adj;
	
	static final int INF = 987654321;
	
	static int dijkstra() {
		PriorityQueue<Tuple> pq = new PriorityQueue<>();
		pq.offer(new Tuple(1, 0, 0));
		// N번 정점까지 M비용을 소모했을 때 최단 거리
		int[][] time = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) Arrays.fill(time[i], INF);
		time[1][0] = 0;
		while (!pq.isEmpty()) {
			Tuple t = pq.poll();
			int here = t.num; int hereCost = t.cost; int hereTime = t.time;
			if (time[here][hereCost] < hereTime) continue;
			for (Tuple e : adj.get(here)) {
				int there = e.num; int thereCost = hereCost + e.cost; int thereTime = hereTime + e.time;
				if (thereCost <= M && time[there][thereCost] > thereTime) {
					time[there][thereCost] = thereTime;
					pq.offer(new Tuple(there, thereCost, thereTime));
				}
			}
		}
		int min = time[N][0];
		for (int i = 1; i <= M; i++) min = Math.min(min, time[N][i]);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>(N);
			for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				adj.get(u).add(new Tuple(v, c, d));
			}
			int ret = dijkstra();
			bw.append(ret == INF ? "Poor KCM" : ret).append("\n");
		}
		System.out.print(bw);
	}

}

class Tuple implements Comparable<Tuple> {
	int num, cost, time;
	
	Tuple(int n, int c, int t) { num = n; cost = c; time = t;}
	
	public int compareTo(Tuple o) { return time - o.time; }
	
}
