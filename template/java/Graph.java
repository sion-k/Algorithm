public class Graph {
	static int N, M;

	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

	static final int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static final int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

	static boolean[][] visit;
	static int[][] dist;
	
	static ArrayList<ArrayList<Pair>> adj;
	adj = new ArrayList<>(N);
	for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
	
	static void dfs(int y, int x) {
		visit[y][x] = true;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d]; int nx = x + dx[d];
			if (!inRange(ny, nx) || visit[ny][nx]) continue;
			dfs(ny, nx);
		}
	}

	// (sy, sx) -> (ey, ex) 최단 거리 반환. 도달 불가능하면 -1 반환
	static int bfs(int sy, int sx, int ey, int ex) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });
		dist = new int[N][M];
		for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
		dist[sy][sx] = 0;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || dist[ny][nx] != -1) continue;
				q.offer(new int[] { ny, nx });
				dist[ny][nx] = dist[y][x] + 1;
			}
		}
		return dist[ey][ex];
	}

	// 정점 src에서 다른 모든 정점까지의 최단거리를 담은 dist[]를 반환
	static int[] dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(start, 0));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			if(dist[here] < cost) continue;
			for (Pair e : adj.get(here)) {
				int there = e.num; int thereCost = cost + e.cost;
				if (dist[there] > thereCost) {
					dist[there] = thereCost;
					pq.offer(new Pair(there, thereCost));
				}
			}
		}
		return dist;
	}

	// 정점 src에서 다른 모든 정점까지의 최단거리를 담은 upper[]를 반환
	// 만약 음수 사이클이 존재하는 경우 빈 배열을 반환한다
	static int[] bellmanFord(int src) {
		int[] upper = new int[V + 1];
		Arrays.fill(upper, INF);
		upper[src] = 0;
		boolean updated = false;
		// N - 1번 완화 시도
		for (int n = 1; n <= N - 1; n++) {
			updated = false;
			for (int here = 1; here <= N; here++) {
				for (Pair edge : adjList.get(here)) {
					int there = edge.num; int cost = edge.cost;
					// (here, there) 간선을 따라 완화 시도
					if (upper[there] > upper[here] + cost) {
						upper[there] = upper[here] + cost;
						updated = true;
					}
				}
			}
			// 모든 간선에 대해 완화시도했는데 완화되지 않으면 최단 경로
			if (!updated) {break;}
		}
		// N번째의 완화 시도
		for (int here = 1; here <= N; here++) {
			for (Pair edge : adjList.get(here)) {
				int there = edge.num; int cost = edge.cost;
				// (here, there) 간선을 따라 완화 시도했는데 완화되고
				// 시작지점에서 그 지점까지 경로가 있는 경우
				if (upper[there] > upper[here] + cost && adjArray[src][there] < INF) {
					return new int[0];
				}
			}
		}
		return upper;
	}

	// 모든 정점간의 최단 거리를 adjArray에 저장
	// 선 조건 : adj(i, j) (간선이 존재하지 않으면 INF로 초기화)
	static void floyd() {
		// 자기 자신으로의 최단 경로는 0
		for (int i = 1; i <= N; i++) {adjArray[i][i] = 0;}
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjArray[i][j] = Math.min(adjArray[i][j], adjArray[i][k] + adjArray[k][j]);
				}
			}
		}
	}

	// 모든 정점간의 도달 가능성 여부를 reachable에 저장
	// 선 조건 : adj(i, j) 간선이 존재하면 true
	static void floydReachable() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					reachable[i][j] = reachable[i][j] || (reachable[i][k] && reachable[k][j]);
				}
			}
		}
	}
}

class Pair implements Comparable<Pair> {
	int num, cost;
	
	public Pair(int n, int c) { num = n; cost = c; }
	
	@Override
	public int compareTo(Pair o) { return cost - o.cost; }
}
