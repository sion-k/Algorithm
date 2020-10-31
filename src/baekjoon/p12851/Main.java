package baekjoon.p12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int K;
	static int[] WAY = new int[100001];

	static boolean inRange(int here) {return 0 <= here && here <= 100000;}

	static int move(int here, int moveWay) {
		switch(moveWay) {
		case 0:
			return here - 1;
		case 1:
			return here + 1;
		default:
			return 2 * here;
		}
	}

	static int BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		boolean[] visit = new boolean[100001];
		visit[start] = true;
		int[] dist = new int[100001];
		Arrays.fill(dist, -1);
		dist[start] = 0;
		WAY[start] = 1;
		if (start == K) {return 0;}

		while (!q.isEmpty()) {
			int here = q.poll();
			System.out.println("here : " + here);
			if (here == K) {return dist[here];}
			visit[here] = true;
			for (int m = 0; m < 3; m++) {
				int there = move(here, m);
				System.out.println("there : " + there);
				if (inRange(there) && !visit[there]) {
					q.offer(there);
					if (dist[there] == -1) {dist[there] = dist[here] + 1;}
					if (dist[there] == dist[here] + 1) {WAY[there] += WAY[here];}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(BFS(N));
		System.out.println(WAY[K]);
	}

}