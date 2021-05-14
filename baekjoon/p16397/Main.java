package baekjoon.p16397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T; static int G;

	static boolean inRange(int here) {return 0 <= here && here <= 99999;}

	static int move(int here, int button) {
		switch (button) {
		case 0:
			return here + 1;
		default :
			here *= 2;
			if (here > 99999) {return -1;}
			int[] d = new int[5];
			for (int i = 4; here != 0; here /= 10, i--) {
				d[i] = here % 10;
			}
			for (int i = 0; i < 5; i++) {
				if (d[i] != 0) {d[i]--; break;}
			}
			int sum = 0;
			int[] pow = {10000, 1000, 100, 10, 1};
			for (int i = 0; i < 5; i++) {
				sum += (d[i] * pow[i]);
			}
			return sum;
		}
	}

	static int BFS(int start) {
		if (start == G) {return 0;}
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		boolean[] booked = new boolean[100000];
		booked[start] = true;
		int[] dist = new int[100000];
		dist[start]  = 0;

		while (!q.isEmpty()) {
			int here = q.poll();
			for (int m = 0; m < 2; m++) {
				int there = move(here, m);
				if (inRange(there) && !booked[there]) {
					if (dist[here] + 1 < T) {q.offer(there);}
					booked[there] = true;
					dist[there] = dist[here] + 1;
					if (there == G) {return dist[there];}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		int ret = BFS(N);
		if (ret == -1) {System.out.println("ANG");}
		else {System.out.println(ret);}
	}

}