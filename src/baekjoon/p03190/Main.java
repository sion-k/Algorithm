package baekjoon.p03190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		// ���� ���� �ð����κ��� X�ʰ� ���� �ڿ� ���� ȸ�� ? 0 : 1;
		int[] turn = new int[10001];
		Arrays.fill(turn, -1);
		for (int i = 0; i < L ;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			turn[time] = st.nextToken().equals("L") ? 0 : 1;
		}
		int t = 0;
		while (s.move()) {
			t++;
			if (turn[t] != -1) {s.dir = Snake.nd[s.dir][turn[t]];}
		}
		System.out.println(t + 1);
	}

}

class Snake {
	static int N; // ������ ũ��
	static boolean[][] APPLE; // ����� �����ϴ��� ����

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	// dir �� [0, 4) �϶� ���� ����, ������ ȸ���ϰ� �ٲ�� ����
	static final int[][] nd = { {2, 3}, {3, 2}, {1, 0}, {0, 1} };

	int dir; // �Ӹ��� ����
	Deque<int[]> body = new LinkedList<>(); // �Ӹ����� �����ؼ� ���������� ��ġ
	// ���� ��ġ �ϴ� ��ġ
	boolean[][] occup;

	public Snake() {
		dir = 3;
		body.offer(new int[] { 0, 0 });
		occup = new boolean[N][N];
		occup[0][0] = true;
	}

	// ���� �ٶ󺸴� �������� �� ĭ ����. �ε����� false ��ȯ
	boolean move() {
		int[] head = body.peekFirst();
		int y = head[0]; int x = head[1];
		int ny = y + dy[dir]; int nx = x + dx[dir];
		// ���̳� �ڱ� ���� �ε����� ���� ��
		if (!inRange(ny, nx) || occup[ny][nx]) {return false;}
		// �Ӹ� ���̸� �ø���
		body.offerFirst(new int[] {ny, nx});
		occup[ny][nx] = true;
		// ����� ���ٸ� ������ �ϳ� ���δ�
		if (!APPLE[ny][nx]) {
			int[] tail = body.peekLast();
			int ty = tail[0]; int tx = tail[1];
			occup[ty][tx] = false;
			body.pollLast();
		// ����� �Դ´�
		} else {
			APPLE[ny][nx] = false;
		}
		return true;
	}

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
}