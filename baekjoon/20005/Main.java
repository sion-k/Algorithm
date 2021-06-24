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
		int y; int x; char name;
		int dist; int dps;
		public Player(int y, int x, char n) {
			this.y = y; this.x = x; this.name = n;
		}
		@Override
		public int compareTo(Player o) {return dist - o.dist;}
		@Override
		public String toString() {return name + " " + dist + " " + dps;}
	}
	static int N; static int M;
	static char[][] MAP;
	static int yB; static int xB;

	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int BFS(int y, int x) {
		boolean[][] BOOKED = new boolean[N][M];
		int[][] DIST = new int[N][M];
		Queue<int[]> q = new LinkedList<>();
		BOOKED[y][x] = true; DIST[y][x] = 0;
		q.offer(new int[] {y, x});

		while (!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 4; next++) {
				int ty = here[0] + dy[next]; int tx = here[1] + dx[next];
				if (inRange(ty, tx) && MAP[ty][tx] != 'X' && !BOOKED[ty][tx]) {
					q.offer(new int[] {ty, tx}); BOOKED[ty][tx] = true;
					DIST[ty][tx] = DIST[here[0]][here[1]] + 1;
					if (ty == yB && tx == xB) {return DIST[ty][tx];}
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
		MAP = new char[N][M];

		int P = Integer.parseInt(st.nextToken());
		ArrayList<Player> fighter = new ArrayList<>(P);
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = row.charAt(j);
				if (MAP[i][j] == 'B') {yB = i; xB = j;}
				if ('a' <= MAP[i][j] && MAP[i][j] <= 'z') {
					fighter.add(new Player(i, j, MAP[i][j]));
				}
			}
		}
		for (int p = 0; p < P; p++) {
			st = new StringTokenizer(br.readLine(), " ");
			char name = st.nextToken().charAt(0);
			for (int i = 0; i < fighter.size(); i++) {
				Player found = fighter.get(i);
				if (found.name == name) {
					found.dist = BFS(found.y, found.x);
					found.dps = Integer.parseInt(st.nextToken());
				}
			}
		}
		Collections.sort(fighter);
		int HP = Integer.parseInt(br.readLine());
		int partyNum = 0; int t = 0; int p = 0; int totalDps = 0;
		while (fighter.get(p).dist == -1) {p++;}
		while (HP > 0) {
			if (p < P && fighter.get(p).dist == t) {
				totalDps += fighter.get(p).dps; p++; partyNum++;
				continue;
			}
			HP -= totalDps; t++;
		}
		System.out.println(partyNum);
	}

}
