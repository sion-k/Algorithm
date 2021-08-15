import java.io.*;
import java.util.*;

public class Main {
	static int V;
	static boolean[] booked;
	static ArrayList<ArrayList<Integer>> adj;
	
	static boolean bfsAll() {
		bfs(1);
		for (int i = 1; i <= V; i++)
			if (!booked[i]) return false;
		return true;
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		booked[start] = true;
		while (!q.isEmpty()) {
			int here = q.poll();
			for (int there : adj.get(here)) if (!booked[there]) {
				q.offer(there);
				booked[there] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[] C = new int[V + 1];
		adj = new ArrayList<>(V);
		for (int i = 0; i <= V; i++) adj.add(new ArrayList<>());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
			C[u]++; C[v]++;
		}
		int cnt = 0;
		for (int i = 1; i <= V; i++) if (C[i] % 2 == 1) cnt++;
		booked = new boolean[V + 1];
		if (!bfsAll()) cnt += 3;
		System.out.println(cnt == 0 || cnt == 2 ? "YES" : "NO");
	}

}
