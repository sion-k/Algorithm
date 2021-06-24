import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Planet> S;
	static ArrayList<ArrayList<Integer>> adj;
	
	// S[here] 행성에 S[i]를 삽입
	static void insert(int parent, int here, int i) {
		for (int there : adj.get(here)) if (there != parent && S.get(there).include(S.get(i))) {
			insert(here, there, i); return;
		}
		adj.get(here).add(i); adj.get(i).add(here);
	}
	
	static int bfs(int u, int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(u);
		int[] dist = new int[S.size()];
		Arrays.fill(dist, -1);
		dist[u] = 0;
		while (!q.isEmpty()) {
			int here = q.poll();
			for (int there : adj.get(here)) if (dist[there] == -1) {
				q.offer(there);
				dist[there] = dist[here] + 1;
				if (there == v) return dist[there];
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(br.readLine());
			S = new ArrayList<>(n + 3);
			S.add(new Planet(0, 0, 10000));
			adj = new ArrayList<>(n + 3);
			for (int i = 0; i < n + 3; i++) adj.add(new ArrayList<>());
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				S.add(new Planet(cx, cy, r));
			}
			S.add(new Planet(x1, y1, 0));
			S.add(new Planet(x2, y2, 0));
			Collections.sort(S);
			for (int i = 1; i < S.size(); i++) insert(0, 0, i);
			bw.append(bfs(n + 1, n + 2) - 2).append("\n");
		}
		System.out.print(bw);
	}

}
class Planet implements Comparable<Planet> {
	int x, y, r;
	
	Planet(int x, int y, int r) { this.x = x; this.y = y; this.r = r; }
	
	// 행성 p를 포함하는지 여부
	boolean include(Planet p) {
		double dist = Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		if (dist >= r + p.r) return false;
		return r > p.r;
	}

	@Override
	public int compareTo(Planet o) { return o.r - r; }
	
}
