package baekjoon.p13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N; static int K;
	
	// here에서 way번째 방법으로 도착하는 곳 반환
	static int move(int here, int way) {
		switch (way) {
		case 0 : return here - 1;
		case 1 : return here + 1;
		case 2 : return 2 * here;
		}
		return -1;
	}
	
	static boolean inRange(int here) { return 0 <= here && here <= 100000; }
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		int[] dist = new int[200000];
		Arrays.fill(dist, -1);
		dist[N] = 0;
		int[] parent = new int[200000];
		Arrays.fill(parent, -1);
		parent[N] = N;
		while (!q.isEmpty() || parent[K] == -1) {
			int here = q.poll();
			for (int d = 0; d < 3; d++) {
				int there = move(here, d);
				if (!inRange(there) || dist[there] != -1) continue;
				q.offer(there);
				dist[there] = dist[here] + 1;
				parent[there] = here;
			}
		}
		System.out.println(dist[K]);
		Stack<Integer> s = new Stack<>();
		for (int p = K; p != N; p = parent[p])
			s.push(p);
		s.push(N);
		StringBuilder ans = new StringBuilder();
		while (!s.isEmpty()) ans.append(s.pop()).append(" ");
		System.out.println(ans.toString().trim());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bfs();
	}
	
}