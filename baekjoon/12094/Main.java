import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] S;
	
	static void move(int dir) {
		switch (dir) {
		case 0:
			for (int j = 0; j < N; j++) {
				Queue<Integer> q = new LinkedList<>();
				for (int i = 0; i < N; i++) {
					if (S[i][j] != 0) q.offer(S[i][j]);
					S[i][j] = 0;
				}
				int t = 0;
				while (!q.isEmpty()) {
					int p = q.poll();
					if (S[t][j] == 0) S[t][j] = p;
					else if (S[t][j] == p) S[t++][j] *= 2;
					else S[++t][j] = p;
				}
			}
			break;
		case 1:
			for (int j = 0; j < N; j++) {
				Queue<Integer> q = new LinkedList<>();
				for (int i = N - 1; i >= 0; i--) {
					if (S[i][j] != 0) q.offer(S[i][j]);
					S[i][j] = 0;
				}
				int t = N - 1;
				while (!q.isEmpty()) {
					int p = q.poll();
					if (S[t][j] == 0) S[t][j] = p;
					else if (S[t][j] == p) S[t--][j] *= 2;
					else S[--t][j] = p;
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				Queue<Integer> q = new LinkedList<>();
				for (int j = 0; j < N; j++) {
					if (S[i][j] != 0) q.offer(S[i][j]);
					S[i][j] = 0;
				}
				int t = 0;
				while (!q.isEmpty()) {
					int p = q.poll();
					if (S[i][t] == 0) S[i][t] = p;
					else if (S[i][t] == p) S[i][t++] *= 2;
					else S[i][++t] = p;
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				Queue<Integer> q = new LinkedList<>();
				for (int j = N - 1; j >= 0; j--) {
					if (S[i][j] != 0) q.offer(S[i][j]);
					S[i][j] = 0;
				}
				int t = N - 1;
				while (!q.isEmpty()) {
					int p = q.poll();
					if (S[i][t] == 0) S[i][t] = p;
					else if (S[i][t] == p) S[i][t--] *= 2;
					else S[i][--t] = p;
				}
			}
			break;
		}
		
	}
	
	static int MAX = 0;
	
	static int getMax() {
		int max = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				max = Math.max(max, S[i][j]);
		return max;
	}
	
	static boolean equals(int[][] here, int[][] there) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (here[i][j] != there[i][j]) return false;
		return true;
	}
	
	static void btk(int depth) {
		if (depth == 10) { MAX = Math.max(MAX, getMax()); return; }
		if (getMax() * (1 << (10 - depth)) <= MAX) return;
		int[][] here = new int[N][];
		for (int i = 0; i < N; i++) here[i] = S[i].clone();
		for (int d = 0; d < 4; d++) {
			move(d);
			if (equals(here, S)) continue;
			btk(depth + 1);
			for (int i = 0; i < N; i++)
				S[i] = here[i].clone();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
				MAX = Math.max(MAX, S[i][j]);
			}
		}
		btk(0);
		System.out.println(MAX);
	}
	
}
