package baekjoon.p09205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] POS; // 정점들
	static boolean[][] EDGE;
	static boolean[] VISIT;
	
	// 정점 번호 here로 부터 목적지(마지막 정점 : POS.length - 1)에 도달 가능한지 반환
	static boolean dfs(int here) {
		if (here == POS.length - 1) {return true;}
		VISIT[here] = true;
		for (int next =0; next < POS.length; next++) {
			if(EDGE[here][next] && !VISIT[next]) {
				if(dfs(next)) {return true;}
			}
		}
		return false;
	}
	
	static boolean reachable(int[] from, int[] to) {
		int dy = Math.abs(from[0] - to[0]); int dx = Math.abs(from[1] - to[1]);
		return ((dy + dx) <= 1000);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int conv = Integer.parseInt(br.readLine());
			POS = new int[conv + 2][2];
			VISIT = new boolean[POS.length];
			// 정점 입력
			for(int i = 0; i < POS.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				POS[i] = new int[] {x, y};
			}
			// 간선 생성
			EDGE = new boolean[POS.length][POS.length];
			for (int i = 0; i < POS.length - 1; i++) {
				for (int j = i; j < POS.length; j++) {
					if (reachable(POS[i], POS[j])) {
						EDGE[i][j] = EDGE[j][i] = true;
					}
				}
			}
			if(dfs(0)) {System.out.println("happy");} 
			else {System.out.println("sad");}
		}
		br.close();
	}

}