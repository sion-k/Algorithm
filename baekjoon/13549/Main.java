import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int K;

	static boolean inRange(int here) {return 0 <= here && here <= 100000;}

	static int move(int here, int moveWay) {
		switch(moveWay) {
		case 0:
			return here - 1;
		case 1:
			return here + 1;
		default:
			return 2 * here;
		}
	}

	static int[] Dijkstra(int src) {
		int[] dist = new int[100001];
		Arrays.fill(dist, 100000);
		dist[src] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));

		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			// 더 나은 경로를 알고 있다면 무시
			if(dist[here] < cost) {continue;}
			for (int m = 0; m < 3; m++) {
				int there = move(here, m);
				if (!inRange(there)) {continue;}
				int nextDist = cost + (m == 2 ? 0 : 1);
				// 기존에 발견한 것보다 더 짧은 경로를 발견 한 경우 최신화
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(Dijkstra(N)[K]);
	}

}

class Pair implements Comparable<Pair> {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}
