import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<ArrayList<Tuple>> adj;
	
	// 정점의 개수는 최대 N * 10이고 간선의 길이는 최대 10^5
	// 그런데 간선의 길이에 모두 10!을 곱했으므로 10^5 * 10!
	// 가장 긴 최단경로의 길이는 10^10 * 10! 이므로 이보다 큰 값 사용
	static final long INF = Long.MAX_VALUE;
	
	static long dijkstra() {
		PriorityQueue<Tuple> pq = new PriorityQueue<>();
		pq.offer(new Tuple(1, 1, 0L));
		// (정점 번호, 그곳에 도착한 속도) 그래프 모델링
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
					// 속도가 1 미만이 되거나 속도 제한을 넘는 경우
					if (!(1 <= thereSpeed && thereSpeed <= edge.limit))
						continue;
					int there = edge.num;
					// 간선에 10!을 곱했으므로 무조건 나누어 떨어짐
					long thereCost = cost + edge.cost / thereSpeed;
					if (dist[there][thereSpeed] > thereCost) {
						dist[there][thereSpeed] = thereCost;
						pq.offer(new Tuple(there, thereSpeed, thereCost));
					}
				}
			}
		}
		// 어떤 속도로 도착하건 최단 시간을 구한다
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
		// 모든 간선에 10!이 곱해져있으므로 10!로 나누어준다
		// 실수 오차를 막기 위해서 정수부 연산과 실수부 연산을 분리한다.
		long x = dijkstra();
		System.out.print(x / 3628800);
		x %= 3628800;
		double y = (double)x / 3628800;
		System.out.println(String.format("%.9f", y).toString().substring(1));
	}

}

// 간선 혹은 pq에 넣을 정점을 표현하는 Tuple
class Tuple implements Comparable<Tuple> {
	int num, speed, limit;
	long cost;
	
	// 간선으로 사용하는 경우
	Tuple(int n, long c, int l) {
		num = n; cost = c; limit = l;
	}
	
	// pq에 넣을 정점으로 사용하는 경우
	Tuple(int n, int s, long c) {
		num = n; speed = s; cost = c;
	}
	
	@Override
	public int compareTo(Tuple o) { return Long.compare(cost, o.cost); }

}
