import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// (동, 서, 남, 북) 프린터 좌표계 이동
	static final int[] dy = {0, 0, 1, -1};
	static final int[] dx = {1, -1, 0, 0};

	static double[] EWSN = new double[4];

	static double btk(boolean[][] visit, int y, int x, int n) {
		if (n == 0) return 1;
		double sum = 0;
		for (int m = 0; m < 4; m++) {
			int ny = y + dy[m]; int nx = x + dx[m];
			if (!visit[ny][nx]) {
				visit[ny][nx] = true;
				sum += (EWSN[m] * btk(visit, ny, nx, n - 1));
				visit[ny][nx] = false;
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 4; i++)
			EWSN[i] = (Integer.parseInt(st.nextToken()) / 100d);
		boolean[][] visit = new boolean[50][50];
		// (25, 25)에서 시작한다고 가정
		visit[25][25] = true;
		System.out.println(btk(visit, 25, 25, N));
	}

}
