import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static Queue<int[]> q;
	static boolean[][] booked;
	static int[][] civ; // civ[y][x]에 위치하는 문명 번호
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }
	
	static DisjointSet s;
	
	// BFS도중 큐에 들어간 새로운 위치 정보들을 기반으로
	// 인접한 위치에 다른 문명이 있다면 합쳐준다
	static void merge() {
		for (int[] p : q) {
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx)) continue;
				if (civ[ny][nx] != -1 && s.union(civ[y][x], civ[ny][nx]))
					K--;
			}
		}
	}
	
	// 문명을 딱 1년간만 전파시킨다. 도중에 겹치면 합쳐준다.
	// 전파시킨 후에는 모두 만났는지 여부까지 확인해서 반환
	static boolean spread() {
		Queue<int[]> nq = new LinkedList<>();
		for (int[] p : q) {
			int y = p[0]; int x = p[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx)) continue;
				if (booked[ny][nx]) {
					if (s.union(civ[y][x], civ[ny][nx])) K--;
					if (K == 1) return true;
				} else {
					nq.offer(new int[] { ny, nx });
					booked[ny][nx] = true;
					civ[ny][nx] = civ[y][x];
				}
			}
		}
		q = nq;
		merge();
		return K == 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		booked = new boolean[N][N];
		civ = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(civ[i], -1);
		s = new DisjointSet(K);
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			q.offer(new int[] { y, x });
			booked[y][x] = true;
			civ[y][x] = i;
		}
		int ret = 0;
		merge();
		if (K != 1) {
			for (int year = 0; year < 2000; year++) {
				if (spread()) { ret = year + 1; break; }
			}
		}
		System.out.println(ret);
	}

}

class DisjointSet {
	int[] parent; int[] rank;
	
	public DisjointSet(int n) {
		parent = new int[n]; for (int i = 0; i < n; i++) parent[i] = i;
		rank = new int[n];
	}

	// 합쳐지는지 여부 반환
	boolean union(int u, int v) {
		u = find(u); v = find(v);
		if (u == v) return false;
		if (rank[u] > rank[v]) { int temp = u; u = v; v = temp; }
		parent[u] = v;
		if (rank[u] == rank[v]) rank[v]++;
		return true;
	}

	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

}
