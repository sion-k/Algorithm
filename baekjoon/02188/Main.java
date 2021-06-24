import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] capacity;
	static int[][] flow;
	
	static int networkFlow(int source, int sink) {
		int totalFlow = 0;
		// 너비 우선 탐색으로 증가 경로를 찾는다
		while (true) {
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			int[] parent = new int[N + M + 2];
			Arrays.fill(parent, -1);
			parent[source] = source;
			// sink에 도달할 때 까지 너비 우선 탐색
			while (!q.isEmpty() && parent[sink] == -1) {
				int here = q.poll();
				// 잔여 용량이 남은 간선을 따라 탐색
				for (int there = 0; there < N + M + 2; there++)
					if (capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
						q.offer(there);
						parent[there] = here;
					}
			}
			// 증가 경로가 없으면 종료
			if (parent[sink] == -1) break;
			// 모든 용량은 1이므로 증가경로에 1만큼 흘려보낸다
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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 0번째 정점이 source, N + M + 1번째 정점이 sink
		capacity = new int[N + M + 2][N + M + 2]; flow = new int[N + M + 2][N + M + 2];
		// [1, N]정점이 소, [N + 1, N + M]정점이 축사를 나타낸다
		for (int i = 1; i <= N; i++) {
			capacity[0][i] = 1; // source와 소 사이의 간선
			st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			for (int j = 0; j < K; j++) capacity[i][N + Integer.parseInt(st.nextToken())] = 1;
		}
		// 축사와 sink 사이의 간선
		for (int i = 1; i <= M; i++) capacity[N + i][N + M + 1] = 1;
		System.out.println(networkFlow(0, N + M + 1));
	}
	
}
