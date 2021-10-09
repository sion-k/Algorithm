import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static char[][] S;

	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

	// 새로 얻은 문서의 개수와 키의 개수를 반환한다.
	static int[] bfs(boolean[] key) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0 });
		boolean[][] booked = new boolean[N][M];
		booked[0][0] = true;
		int sum = 0;
		int newKey = 0;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) { 
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || S[ny][nx] == '*' || booked[ny][nx]) continue;
				if ('A' <= S[ny][nx] && S[ny][nx] <= 'Z' && !key[S[ny][nx] - 'A']) continue;
				if ('a' <= S[ny][nx] && S[ny][nx] <= 'z') {
					key[S[ny][nx] - 'a'] = true;
					newKey++;
				}
				if (S[ny][nx] == '$') sum++;
				q.offer(new int[] { ny, nx });
				booked[ny][nx] = true;
				S[ny][nx] = '.';
			}
		}
		if (sum == 0 && newKey == 0) return new int[0];
		return new int[] { sum, newKey };
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()) + 2;
			M = Integer.parseInt(st.nextToken()) + 2;
			S = new char[N][M];
			for (int i = 0; i < N; i++) Arrays.fill(S[i], '.');
			for (int i = 1; i < N - 1; i++) {
				String line = br.readLine();
				for (int j = 1; j < M - 1; j++) S[i][j] = line.charAt(j - 1);
			}
			boolean[] key = new boolean[26];
			String K = br.readLine();
			if (!K.equals("0")) {
				for (int i = 0; i < K.length(); i++) key[K.charAt(i) - 'a'] = true;
			}
			int sum = 0; int[] p = new int[2];
			while ((p = bfs(key)).length != 0) sum += p[0];
			bw.append(sum).append("\n");
		}
		System.out.print(bw);
	}

}
