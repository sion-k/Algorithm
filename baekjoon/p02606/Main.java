package baekjoon.p02606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	// ���� ��� ǥ����
	static int M;
	static boolean[][] EDGE;
	// DFS��
	static boolean[] VISIT;
		
	// here��ǻ�Ͱ� �� ���̷����� �ɷ��� �� �׷� ���� �ɸ��� �� ��ǻ���� �� ��ȯ
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
		System.out.println(dfs(1) - 1);// 1�� ��ǻ�� ����
	}

}