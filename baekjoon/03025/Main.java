import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] S;
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static boolean movableLeft(int y, int x) {
		return inRange(y, x - 1) && S[y][x - 1] == '.' &&
		inRange(y + 1, x - 1) && S[y + 1][x - 1] == '.';
	}
	
	static boolean movableRight(int y, int x) {
		return inRange(y, x + 1) && S[y][x + 1] == '.' &&
		inRange(y + 1, x + 1) && S[y + 1][x + 1] == '.';
	}
	
	static void fall(int y, int x) {
		while (y < N) {
			S[y][x] = 'O';
			if (!inRange(y + 1, x) || S[y + 1][x] == 'X') break;
			if (S[y + 1][x] == 'O') {
				if (movableLeft(y, x)) {
					S[y++][x--] = '.';
				} else if (movableRight(y, x)) {
					S[y++][x++] = '.';
				} else {
					break;
				}
			} else {
				S[y++][x] = '.';
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = line.charAt(j);
		}
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			int x = Integer.parseInt(br.readLine()) - 1;
			fall(0, x);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				bw.append(S[i][j]);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}
