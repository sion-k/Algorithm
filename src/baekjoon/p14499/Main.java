package baekjoon.p14499;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	// �����ϳ�
	static final int[] dy = {0, 0, -1, 1};
	static final int[] dx = {1, -1, 0, 0};

	static final boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] MAP = new int[N][M];
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				MAP[i][j] = Integer.parseInt(st.nextToken());
		}
		Dice dice = new Dice(y, x);
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			int ny = dice.y + dy[d - 1]; int nx = dice.x + dx[d - 1];
			if (!inRange(ny, nx)) {continue;}
			dice.y = ny; dice.x = nx;
			dice.roll(d);
			if (MAP[ny][nx] == 0) {
				MAP[ny][nx] = dice.down;
			} else {
				dice.down = MAP[ny][nx];
				MAP[ny][nx] = 0;
			}
			bw.write(String.valueOf(dice.up));
			bw.newLine();
		}
		bw.close();
	}

}

class Dice {
	// �� �� �� �� �� �Ʒ� ���⿡ ������ ��
	int north; int south; int west; int east; int up; int down;
	int y; int x; // �ֻ����� ���� ��ġ

	public Dice(int y, int x) {this.y = y; this.x = x;}

	// �����ϳ� [1, 4] �������� �ֻ����� ������ (������ �� �� �־�� ��)
	public void roll(int d) {
		switch (d) {
		case 1 :
			int temp = west;
			west = down; down = east; east = up;
			up = temp;
			break;
		case 2:
			temp = west;
			west = up; up = east; east = down;
			down = temp;
			break;
		case 3:
			temp = north;
			north = up; up = south; south = down;
			down = temp;
			break;
		case 4:
			temp = north;
			north = down; down = south; south = up;
			up = temp;
			break;
		}
	}

}