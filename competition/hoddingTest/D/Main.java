package competition.hoddingTest.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] adj;
	static final int INF = 987654321;
	
	// 모든 정점간의 최단 거리를 adjArray에 저장
	// 선 조건 : adj(i, j) (간선이 존재하지 않으면 INF로 초기화)
	static void floyd() {
		// 자기 자신으로의 최단 경로는 0
		for (int i = 1; i <= N; i++) {adj[i][i] = 0;}
		for (int k = 1; k <= N; k++)
			for (int i = 1; i <= N; i++)
				for (int j = 1; j <= N; j++)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
	}
	
	// p와 q에 치킨집을 지었을 때, 모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합
	static int f(int p, int q) {
		int sum = 0;
		for (int i = 1; i <= N; i++) sum += 2 * Math.min(adj[i][p], adj[i][q]);
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) Arrays.fill(adj[i], INF);
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adj[A][B] = 1;
			adj[B][A] = 1;
		}
		floyd();
		int min = INF; int p = 0; int q = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				int sum = f(i, j);
				if (sum < min) {
					min = sum;
					p = i; q = j;
				} else if (sum == min) {
					if (i < p) {
						p = i; q = j;
					} else if (i == p && j < q) {
						p = i; q = j;
					}
				}
			}
		}
		System.out.println(p + " " + q + " " + min);
	}

}