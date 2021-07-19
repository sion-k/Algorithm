import java.io.*;
import java.util.*;

public class Main {
	
	static int bfs(int u, int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(u);
		int[] dist = new int[2048];
		Arrays.fill(dist, -1);
		dist[u] = 0;
		while (!q.isEmpty()) {
			int here = q.poll();
			int range = 9;
			while (range - 1 >= 0 && (here & (1 << range)) == 0) range--;
			for (int i = 0; i < range; i++) {
				int there = here ^ (1 << i);
				if (dist[there] == -1) {
					q.offer(there);
					dist[there] = dist[here] + 1;
				}
			}
			if (here + 1 < 2048 && dist[here + 1] == -1) {
				q.offer(here + 1);
				dist[here + 1] = dist[here] + 1;
			}
			if (here - 1 >= 0 && dist[here - 1] == -1) {
				q.offer(here - 1);
				dist[here - 1] = dist[here] + 1;
			}
		}
		return dist[v];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine(), 2);
		int K = Integer.parseInt(br.readLine(), 2);
		System.out.println(bfs(L, K));
	}

}
