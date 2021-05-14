package baekjoon.p02606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	// 인접 행렬 표현법
	static int M;
	static boolean[][] EDGE;
	// DFS용
	static boolean[] VISIT;
		
	// here컴퓨터가 웜 바이러스에 걸렸을 때 그로 인해 걸리는 총 컴퓨터의 수 반환
	static int dfs(int here)  {
		int infect = 1;
		VISIT[here] = true;
		for (int next = 1; next <= N; next++) {
			if(EDGE[here][next] && !VISIT[next]) {
				infect += dfs(next);
			}
		}
		return infect;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		VISIT = new boolean[N + 1];
		EDGE = new boolean[N + 1][N + 1];
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int here = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			EDGE[here][next] = EDGE[next][here]  = true;
		}
		br.close();
		System.out.println(dfs(1) - 1);// 1번 컴퓨터 제외
	}

}