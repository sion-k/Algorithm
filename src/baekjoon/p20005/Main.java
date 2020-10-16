package baekjoon.p20005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Player implements Comparable<Player> {
		 char name; int y; int x; int dps; int cost;
		public Player(char n, int y, int x) {
			name = n; this.y = y; this.x = x;
		}
		@Override
		public int compareTo(Player o) {return cost - o.cost;}
	}

	static int N;
	static int M;
	static boolean[][] MOVABLE; //이동할 수 있는지 여부
	static boolean[][] BOOKED;
	static int[][] DIST;// 최단 거리
	static int yB; static int xB; // 보스의 위치

	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// (y, x) 에서 보스의 위치까지 최소 도달 시간 반환 (불가능하면 -1)
	static int BFS(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {y, x}); BOOKED[y][x] = true;
		DIST[y][x] = 0;

		while(!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 4; next++) {
				int ty = here[0] + dy[next]; int tx = here[1] + dx[next];
				if (inRange(ty, tx) && !BOOKED[ty][tx]) {
					if (ty == yB && tx == xB) {
						return DIST[here[0]][here[1]] + 1;
					}
					q.offer(new int[] {ty, tx}); BOOKED[ty][tx] = true;
					DIST[ty][tx] = DIST[here[0]][here[1]] + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MOVABLE = new boolean[N][M];
		int P = Integer.parseInt(st.nextToken());
		ArrayList<Player> player = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				char here = row.charAt(j);
				MOVABLE[i][j] = !(here == 'X');
				if (here == 'B') {yB = i; xB = j;}
				if ('a' <= here && here <= 'z') {
					player.add(new Player(here, i, j));
				}
			}
		}
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char name = st.nextToken().charAt(0);
			for (int p = 0; p < player.size(); p++) {
				Player found = player.get(p);
				if (found.name == name) {
					found.dps = Integer.parseInt(st.nextToken());
					BOOKED = new boolean[N][M];
					DIST = new int[N][M];
					found.cost = BFS(found.y, found.x);
				}
			}
		}
		Collections.sort(player);
		int HP = Integer.parseInt(br.readLine());
		int t = player.get(0).cost + 1; int dps = player.get(0).dps;
		int next = 1;
		while (HP > 0) {
			if (next < player.size() && t > player.get(next).cost) {
				dps += player.get(next).dps; next++;
			}
			HP -= dps; t++;
		}
		System.out.println(P - next);
		br.close();
	}

}