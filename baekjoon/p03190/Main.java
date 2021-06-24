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
		// 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽 회전 ? 0 : 1;
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
	static int N; // 보드의 크기
	static boolean[][] APPLE; // 사과가 존재하는지 여부

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	// dir 이 [0, 4) 일때 각각 왼쪽, 오른쪽 회전하고 바뀌는 방향
	static final int[][] nd = { {2, 3}, {3, 2}, {1, 0}, {0, 1} };

	int dir; // 머리의 방향
	Deque<int[]> body = new LinkedList<>(); // 머리부터 시작해서 꼬리까지의 위치
	// 몸이 위치 하는 위치
	boolean[][] occup;

	public Snake() {
		dir = 3;
		body.offer(new int[] { 0, 0 });
		occup = new boolean[N][N];
		occup[0][0] = true;
	}

	// 현재 바라보는 방향으로 한 칸 전진. 부딪히면 false 반환
	boolean move() {
		int[] head = body.peekFirst();
		int y = head[0]; int x = head[1];
		int ny = y + dy[dir]; int nx = x + dx[dir];
		// 벽이나 자기 몸에 부딪히면 게임 끝
		if (!inRange(ny, nx) || occup[ny][nx]) {return false;}
		// 머리 길이를 늘린다
		body.offerFirst(new int[] {ny, nx});
		occup[ny][nx] = true;
		// 사과가 없다면 꼬리를 하나 줄인다
		if (!APPLE[ny][nx]) {
			int[] tail = body.peekLast();
			int ty = tail[0]; int tx = tail[1];
			occup[ty][tx] = false;
			body.pollLast();
		// 사과를 먹는다
		} else {
			APPLE[ny][nx] = false;
		}
		return true;
	}

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
}