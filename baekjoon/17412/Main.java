import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] capacity; static int[][] flow;

	// (source, sink)간의 최대 유량 반환
	static int networkFlow(int source, int sink) {
		int totalFlow = 0;
		// 증가 경로를 최대한 찾는다
		while (true) {
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			int[] parent = new int[N + 1];
			Arrays.fill(parent, -1);
			parent[source] = source;
			while (!q.isEmpty() && parent[sink] == -1) {
				int here = q.poll();
				for (int there = 1; there <= N; there++)
					if (capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
						q.offer(there);
						parent[there] = here;
					}
			}
			if (parent[sink] == -1) break;
			for (int p = sink; p != source; p = parent[p]) {
				flow[parent[p]][p]++;
				flow[p][parent[p]]--;
			}
			totalFlow++;
		}
		return totalFlow;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		capacity = new int[N + 1][N + 1];
		flow = new int[N + 1][N + 1];
		int P = Integer.parseInt(st.nextToken());
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			capacity[u][v] = 1;
		}
		System.out.println(networkFlow(1, 2));
	}

}
