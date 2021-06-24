package baekjoon.p02174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Robot.N = N; Robot.M = M;
		Robot.MAP = new boolean[N][M];
		st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int O = Integer.parseInt(st.nextToken());
		Robot[] robot = new Robot[R];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			// 좌표계 변환, 스크린 좌표계이며, 로봇의 위치는 왼쪽 위로 대응시킨다
			y = N - y; x = x - 1;
			robot[i] = new Robot(y, x, d);
		}
		Robot.ROBOTS = robot;
		int X = 0; int Y = 0; int check = 0;
		for (int i = 0; i < O; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			X = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				switch (command) {
				case 'L' :
					robot[X - 1].turnLeft();
					break;
				case 'R' :
					robot[X - 1].turnRight();
					break;
				case 'F' :
					check = robot[X - 1].forward();
					Y = check;
				}
				if (check != 0) {break;}
			}
			if (check != 0) {break;}
		}
		if (check == 0) {
			System.out.println("OK");
		} else if (check < 0) {
			System.out.println("Robot " + X + " crashes into the wall");
		} else {
			System.out.println("Robot " + X + " crashes into robot "+ Y);
		}

	}

}

class Robot {
	int y; int x; int d;

	public Robot(int y, int x, char d) {
		this.y = y; this.x = x;
		switch (d) {
		case 'N' : this.d = 1; break;
		case 'S' : this.d = 2; break;
		case 'W' : this.d = 3; break;
		case 'E' : this.d = 4; break;
		}
		MAP[y][x] = true;
	}

	static Robot[] ROBOTS;
	static int N; static int M;
	static boolean[][] MAP;

	static final int[] turnLeft = {0, 3, 4, 2, 1};
	static final int[] turnRight = {0, 4, 3, 1, 2};

	static final int[] dy = {0, -1, 1, 0, 0};
	static final int[] dx=  {0, 0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	void turnLeft() {d = turnLeft[d];}
	void turnRight() {d = turnRight[d];}

	// 이상 없는 경우 0, 범위를 벗어나는 경우 -1, 다른 로봇에 부딪히는 경우 그 로봇의 번호
	int forward() {
		int ny = y + dy[d]; int nx = x + dx[d];
		if (!inRange(ny, nx)) return -1;
		if (MAP[ny][nx]) {
			int collide = 0;
			for (int i = 0; i < ROBOTS.length; i++)
				if (ny == ROBOTS[i].y && nx == ROBOTS[i].x) {
					collide = i; break;
				}
			return collide + 1;
		}
		MAP[y][x] = false;
		y = ny; x = nx;
		MAP[ny][nx] = true;
		return 0;
	}

	@Override
	public String toString() {return y + ", " + x + ", " + d;}
}