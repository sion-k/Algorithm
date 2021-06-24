import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int S;

	static boolean inRange(int d, int c) {
		return 0 <= d && d <= 2000 && 0 <= c && c <= 2000;
	}

	static int[] move(int d, int c, int m) {
		switch (m) {
		case 0:
			return new int[] { d, d };
		case 1:
			return new int[] { d + c, c };
		default:
			return new int[] { d - 1, c };
		}
	}

	static int bfs() {
		if (S == 1) {return 0;}
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {1, 0});
		int[][] dist = new int[2001][2001];
		for (int i = 0; i <= 2000; i++) {Arrays.fill(dist[i], -1);}
		dist[1][0] = 0;
		while (!q.isEmpty()) {
			int[] here = q.poll();
			int d = here[0]; int c = here[1];
			for (int m = 0; m < 3; m++) {
				int[] there = move(d, c, m);
				int nd = there[0]; int nc = there[1];
				if (inRange(nd, nc) && dist[nd][nc] == -1) {
					q.offer(new int[] {nd, nc});
					dist[nd][nc] = dist[d][c] + 1;
					if (nd == S) {return dist[nd][nc];}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		System.out.println(bfs());
	}

}
