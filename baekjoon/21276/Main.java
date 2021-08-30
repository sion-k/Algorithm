import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] S = new String[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = st.nextToken();
		Arrays.sort(S);
		Map<String, Integer> m = new HashMap<>();
		for (int i = 0; i < N; i++) m.put(S[i], i);
		int[] indegree = new int[N];
		boolean[][] adj = new boolean[N][N];
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = m.get(st.nextToken());
			int v = m.get(st.nextToken());
			adj[v][u] = true;
			indegree[u]++;
		}
		int cnt = 0;
		for (int i = 0; i < N; i++)
			if (indegree[i] == 0) cnt++;
		bw.append(cnt).append("\n");
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < N; i++) if (indegree[i] == 0) {
			bw.append(S[i]).append(" ");
			q.offer(i);
		}
		bw.append("\n");
		ArrayList<PriorityQueue<Integer>> T = new ArrayList<>(N);
		for (int i = 0; i <= N; i++) T.add(new PriorityQueue<>());
		for (int i = 0; i < N; i++) {
			int here = q.poll();
			for (int there = 0; there < N; there++) if (adj[here][there]) {
				indegree[there]--;
				if (indegree[there] == 0) {
					q.offer(there);
					T.get(here).offer(there);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			bw.append(S[i]).append(" ");
			bw.append(T.get(i).size()).append(" ");
			PriorityQueue<Integer> pq = T.get(i);
			while (!pq.isEmpty()) bw.append(S[pq.poll()]).append(" ");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}
