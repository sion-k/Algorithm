package baekjoon.p03197;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.PortUnreachableException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static boolean[][] S; // ������ �ִ��� ����
	static int[][] M;
	static int[][] L; // �� ������ ��ġ ����
	
	static final int[] dy = {-1, 1, 0, 0}; static final int[] dx = {0, 0, -1, 1};
	
	static boolean inRange(int y, int x) {return 0 <= y && y < R && 0 <= x && x < C;}
	
	// ��� ���ǵ鿡 ���ؼ� ��ĥ�� ������ ����� �����ϴ� �迭 M�� �����
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		M = new int[R][C];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (S[i][j]) M[i][j] = -1;
				else {
					M[i][j] = 0;
					q.offer(new int[] {i, j});
				}
		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (inRange(ny, nx) && M[ny][nx] == -1) {
					q.offer(new int[] {ny, nx});
					M[ny][nx] = M[y][x] + 1;
				}
			}
		}
	}
	
	// t���� ������ ��, �������� ���� �� �ִ��� ����
	static boolean rechable(int t) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(L[0]);
		boolean[][] booked = new boolean[R][C];
		booked[L[0][0]][L[0][1]] = true;
		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (inRange(ny, nx) && M[ny][nx] <= t && !booked[ny][nx]) {
					if (ny == L[1][0] && nx == L[1][1]) return true;
					q.offer(new int[] {ny, nx});
					booked[ny][nx] = true;
				}
			}
		}
		return false;
	}
	
	static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++)
				System.out.print(M[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		S = new boolean[R][C]; L = new int[2][2];
		int p = 0;
		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				S[i][j] = row.charAt(j) == 'X';
				if (row.charAt(j) == 'L') {L[p] = new int[] {i, j}; p++;}
			}
		}
		bfs();
		int lo = 0; int hi = 1500;
		// rechable[lo] == false && rechable[hi] == true�� �����ϴ� hi��ȯ
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (rechable(mid)) hi = mid;
			else lo = mid;
		}
		System.out.println(hi);
	}

}