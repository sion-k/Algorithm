package baekjoon.p2667;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	// �� �� �� �� ��
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	static int N; // ������ ũ��
	// ���� �ִ��� ������ ����
	static boolean[][] HOUSE;
	// �̹� �湮�� ��
	static boolean[][] VISIT;
	
	// ������ �� ��
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < N;}
	
	// ���� �ִ� (y, x)���� dfs
	static int dfs(int y, int x) {
		int sum = 1; // (y, x)���� ���� �����Ƿ�
		VISIT[y][x] = true;
		for (int move = 0; move < 4; move++) {
			int ty = y + dy[move]; int tx = x + dx[move];
			// ���� �� ���̰�, ���� ������, �湮���� �ʾ��� ��쿡��
			if(inRange(ty, tx) && HOUSE[ty][tx] &&!VISIT[ty][tx]) {
				sum += dfs(ty, tx);
			}
		}
		return sum;
	}
	
	// ���� ��ü�� dfs
	static void dfsAll() {
		for (int i =0 ; i< N; i++) {
			for (int j = 0; j < N; j++) {
				if(HOUSE[i][j] &&!VISIT[i][j]) {pq.add(dfs(i, j));}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		HOUSE = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for(int j = 0; j < N; j++) {
				char h = row.charAt(j);
				HOUSE[i][j] = (h == '1') ? true : false;
			}
		}
		br.close();
		VISIT = new boolean[N][N];
		
		dfsAll();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(pq.size()));
		bw.newLine();
		while (!pq.isEmpty()) {
			bw.write(String.valueOf(pq.poll()));
			bw.newLine();
		}
		bw.close();
	}
}