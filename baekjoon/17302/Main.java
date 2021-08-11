import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] S;
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static char flip(char c) { return c == 'W' ? 'B' : 'W'; }
	
	static void reverse(int y, int x) {
		S[y][x] = flip(S[y][x]);
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d]; int nx = x + dx[d];
			if (!inRange(ny, nx)) continue;
			S[ny][nx] = flip(S[ny][nx]);
		}
	}
	
	static void print() {
		StringBuilder bw = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) bw.append(S[i][j]);
			bw.append("\n");
		}
		System.out.print(bw);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new char[N][M]; int[][] R = new int[N][M];
		for (int i = 0; i < N; i++) S[i] = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			Arrays.fill(R[i], 3);
			for (int j = 0; j < M; j++) reverse(i, j);
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (S[i][j] == 'B') { S[i][j] = 'W'; R[i][j] = 2; }
		bw.append(1).append("\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) bw.append(R[i][j]);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}
