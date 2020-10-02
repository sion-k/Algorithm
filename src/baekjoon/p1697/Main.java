package baekjoon.p1697;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N; // 시작 위치
	static int K; // 도착 위치
	static boolean[] BOOKED = new boolean[100001];
	static int[] DIS = new int[100001];

	static boolean inRange(int i) {return 0 <= i && i <= 100000;}

	static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		BOOKED[start] = true;
		DIS[start] = 0;

		while (!q.isEmpty()) {
			int here = q.poll();
			if (here == K) {return DIS[here];}
			int left = here - 1;
			if (inRange(left) && !BOOKED[left]) {
				q.offer(left);
				BOOKED[left] = true;
				DIS[left] = DIS[here] + 1;
			}
			int right = here + 1;
			if (inRange(right) && !BOOKED[right]) {
				q.offer(right);
				BOOKED[right] = true;
				DIS[right] = DIS[here] + 1;
			}
			int warp = here * 2;
			if (inRange(warp) && !BOOKED[warp]) {
				q.offer(warp);
				BOOKED[warp] = true;
				DIS[warp] = DIS[here] + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		System.out.println(bfs(N));
		sc.close();
	}

}
