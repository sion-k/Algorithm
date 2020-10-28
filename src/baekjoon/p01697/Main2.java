package baekjoon.p01697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int N; static int K;

	static boolean inRange(int here) {return 0 <= here && here <= 100000;}

	static int BFS(int start) {
		if (start == K) {return 0;}
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		boolean[] BOOKED = new boolean[100001];
		BOOKED[start] = true;
		int[] DIST = new int[100001];
		DIST[start] = 0;

		while (!q.isEmpty()) {
			int here = q.poll();
			int left = here - 1;
			if (inRange(left) && !BOOKED[left]) {
				q.offer(left);
				BOOKED[left] = true;
				DIST[left] = DIST[here] + 1;
				if (left == K) {return DIST[left];}
			}
			int right = here + 1;
			if (inRange(right) && !BOOKED[right]) {
				q.offer(right);
				BOOKED[right] = true;
				DIST[right] = DIST[here] + 1;
				if (right == K) {return DIST[right];}
			}
			int warp = here * 2;
			if (inRange(warp) && !BOOKED[warp]) {
				q.offer(warp);
				BOOKED[warp] = true;
				DIST[warp] = DIST[here] + 1;
				if (warp == K) {return DIST[warp];}
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
	}

}