package baekjoon.p02583;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int M; // 높이
	static int N; // 너비
	static boolean[][] PAINTED;
	static boolean[][] VISIT;
	
	// 상하좌우
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0 ,0, -1, 1};
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < M && 0 <= x && x < N;
	}
	
	// 발견한 영역들의 크기를 저장하는 우선순위 큐 반환
	static PriorityQueue<Integer> dfsAll() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!PAINTED[i][j] && !VISIT[i][j]) {
					pq.offer(dfs(i, j));
				}
			}
		}
		return pq;
	}
	
	// dfs로 발견한 영역의 칸 수 반환
	static int dfs(int y, int x) {
		int cnt = 1;
		VISIT[y][x] = true;
		for (int next = 0; next < 4; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if (inRange(ty, tx) && !PAINTED[ty][tx] && !VISIT[ty][tx]) {
				cnt += dfs(ty, tx);
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		PAINTED = new boolean[M][N];
		VISIT = new boolean[M][N];
		int K = Integer.parseInt(st.nextToken());
		for (int n = 0; n < K; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a1 = Integer.parseInt(st.nextToken());
			int b1 = Integer.parseInt(st.nextToken());
			int a2 = Integer.parseInt(st.nextToken());
			int b2 = Integer.parseInt(st.nextToken());
			// (y, x) 배열 형태로 변경 (M - b, a)
			int ty1 = M - b1;
			int tx1 = a1;
			int ty2 = M - b2;
			int tx2 = a2;
			// 왼쪽 위와 오른쪽 아래로 변경 (y2, x1) (y1, x2)
			int y1 = ty2; int x1 = tx1;
			int y2 = ty1; int x2 = tx2;
			for (int i = y1; i < y2; i++) {
				for (int j = x1; j < x2; j++) {
					PAINTED[i][j] = true;
				}
			}
		}
		br.close();
		PriorityQueue<Integer> pq = dfsAll();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(pq.size()));
		bw.newLine();
		while (!pq.isEmpty()) {
			bw.write(String.valueOf(pq.poll()));
			if (!pq.isEmpty()) {bw.write(" ");}
		}
		bw.newLine();
		bw.close();
	}

}