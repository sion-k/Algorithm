package baekjoon.p03190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Snake.N = N;
		Snake.APPLE = new boolean[N][N];
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			Snake.APPLE[y][x] = true;
		}
		Snake s = new Snake();
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L ;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		}
	}

}
class Snake {
	static int N; // ������ ũ��
	static boolean[][] APPLE; // ����� �����ϴ��� ����
	int dir; // �Ӹ��� ����
	// �Ӹ����� �����ؼ� ���������� ��ġ
	Deque<int[]> body = new LinkedList<>();
	// ���� ��ġ �ϴ� ��ġ
	boolean[][] occup;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static final int[][] nd = { {2, 3}, {3, 2}, {1, 0}, {0, 1} };

	public Snake() {
		dir = 3;
		body.offerFirst(new int[] { 0, 0 });
		occup = new boolean[N][N];
		occup[0][0] = true;
	}

	boolean move() {
		int[] head = body.peekFirst();
		int y = head[0]; int x = head[1];
		int ny = y + dy[dir]; int nx = x + dx[dir];

	}
}