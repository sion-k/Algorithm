package baekjoon.p12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int[] way;

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
		boolean[] booked = new boolean[100001];
		booked[start] = true;
		int[] dist = new int[100001];
		way = new int[100001];
		way[start] = 1;

		while (!q.isEmpty()) {
			int here = q.poll();
			for (int m = 0; m < 3; m++) {
				int there = move(here, m);
				if (!inRange(there)) {continue;}
				if (!booked[there]) {
					q.offer(there);
					booked[there] = true;
					dist[there] = dist[here] + 1;
					way[there] = way[here];
				} else if(dist[there] == dist[here] + 1) {
					way[there] += way[here];
				}
			}
		}
		return dist[K];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(BFS(N));
		System.out.println(way[K]);
	}

}