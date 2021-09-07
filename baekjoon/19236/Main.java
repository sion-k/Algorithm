import java.util.*;
import java.io.*;

public class Main {

	// 위부터 반시계 방향 순으로 성분을 나타냄
	static final int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static boolean inRange(int y, int x) { return 0 <= y && y < 4 && 0 <= x && x < 4; }

	// S[y][x]에 위치하는 물고기를 반시계 방향을 따라 돌리면서 상어가 아니면 바꿈
	static void move(Pair[][] S, int y, int x) {
		Pair P = S[y][x];
		// 모든 방향에 대해 갈 수 있는지 확인
		for (int i = 0; i <= 8; i++) {
			int ny = y + dy[P.d]; int nx = x + dx[P.d];
			// 범위를 안 벗어나고 상어만 아니면 바로 이동
			if (inRange(ny, nx) && S[ny][nx].i != -1) {
				P.swap(S[ny][nx]);
				break;
			}
			P.rotate();
		}
	}

	// S에 존재하는 모든 물고기를 인덱스 순서대로 이동시킨다
	// 이동 시킨 후 변한 배열을 반환
	static Pair[][] moveAll(Pair[][] S) {
		Pair[][] R = new Pair[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				R[i][j] = S[i][j].copy();
		for (int k = 1; k <= 16; k++)
			for (int y = 0; y < 4; y++)
				for (int x = 0; x < 4; x++)
					if (R[y][x].i == k) {
						move(R, y, x);
						y = 4; break;
					}
		return R;
	}
	
	static void print(Pair[][] S) {
		StringBuilder bw = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				bw.append(S[i][j]).append(" ");
			bw.append("\n");
		}
		System.out.print(bw);
	}

	static int btk(Pair[][] S, int y, int x, int n) {
		if (n == 0) return 0;
		int max = 0;
		Pair P = S[y][x];
		for (int r = 1; r <= 3; r++) {
			int ny = y + r * dy[P.d]; int nx = x + r * dx[P.d];
			if (!inRange(ny, nx) || S[ny][nx].i == 0) continue;
			// 상어 이동
			S[y][x].i = 0; int temp = S[ny][nx].i;
			S[ny][nx].i = -1;
			max = Math.max(max, temp + btk(moveAll(S), ny, nx, n - 1));
			// 상어 이동 초기화
			S[y][x].i = -1; S[ny][nx].i = temp;
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Pair[][] S = new Pair[4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				S[i][j] = new Pair(a, b - 1);
			}
		}
		int temp = S[0][0].i;
		S[0][0].i = -1;
		System.out.println(temp + btk(moveAll(S), 0, 0, 15));
	}

}
class Pair {
	int i, d;

	Pair(int index, int dir) { i = index; d = dir; }

	void swap(Pair o) {
		int temp = i; i = o.i; o.i = temp;
		temp = d; d = o.d; o.d = temp;
	}

	void rotate() { 
		d++;
		if (d == 8) d = 0;
	}

	Pair copy() { return new Pair(i, d); }

	@Override
	public String toString() { return String.format("(%d %d)", i, d); }
}
