package baekjoon.p02146;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] LAND;
	static boolean[][] VISIT;
	static ArrayList<int[]> thisLand;

	// �����¿� �̵�
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	//[0, N)
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	// ��� (i, j)�� ���� DFS
	// DFS�� ȣ�� �� ������ �� ���� �ٸ� �������� �ִ� �ٸ� ���̸� ���
	// ������ �ٸ� �������� �ִ� �ٸ� ������ �ּҰ� ���
	static int DFSAll() {
		int min = 10000;
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) {
				if(LAND[i][j] && !VISIT[i][j]) {
					thisLand = new ArrayList<>();
					DFS(i, j);
					for (int n = 0; n < thisLand.size(); n++) {
						int[] here = thisLand.get(n);
						min = Math.min(min, BFS(here[0], here[1]));
					}
				}
			}
		}
		return min;
	}

	static void DFS(int y, int x) {
		VISIT[y][x] = true;
		thisLand.add(new int[] {y, x});
		for (int next = 0; next < 4; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if (inRange(ty, tx) && LAND[ty][tx] && !VISIT[ty][tx]) {
				DFS(ty, tx);
			}
		}
	}

	// ���� (yS, xS)���� �湮���� ���� ���� ����� �������� �ִ� �Ÿ� ��ȯ
	// �� ������ -1 �Ѱ� �ٸ��� ����
	static int BFS(int yS, int xS) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {yS, xS});
		boolean[][] BOOKED = new boolean[N][N];
		int[][] DIST = new int[N][N];
		BOOKED[yS][xS] = true; DIST[yS][xS] = 0;

		while(!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 4; next++) {
				int ty = here[0] + dy[next]; int tx = here[1] + dx[next];
				if(inRange(ty, tx) && !BOOKED[ty][tx]) {
					if (LAND[ty][tx] && !VISIT[ty][tx]) {
						return DIST[here[0]][here[1]] + 1;
					}
					q.offer(new int[] {ty, tx});
					BOOKED[ty][tx] = true;
					DIST[ty][tx] = DIST[here[0]][here[1]]+ 1;
				}
			}
		}
		return 10000;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		LAND = new boolean[N][N];
		VISIT = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				LAND[i][j] = st.nextToken().equals("1");
			}
		}
		System.out.println(DFSAll() - 1);
	}

}