import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] PAPER;
	static int[] COUNT = new int[3];
	
	static boolean filled(int y1, int x1, int y2, int x2) {
		int comp = PAPER[y1][x1];
		for (int i = y1; i <= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				if (PAPER[i][j] != comp) {return false;}
			}
		}
		return true;
	}
	
	static void tree(int y1, int x1, int y2, int x2) {
		if (filled(y1, x1, y2, x2)) {
			COUNT[PAPER[y1][x1] + 1]++; return;
		}
		int L = (y2 - y1 + 1) / 3; // 한변의 길이(9등분)
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int ty = y1 + i * L; int tx = x1 + j * L;
				tree(ty, tx, ty + L - 1, tx + L - 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PAPER = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				PAPER[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		tree(0, 0, N - 1, N - 1);
		for (int i = 0; i < 3; i++) {System.out.println(COUNT[i]);}
	}

}
