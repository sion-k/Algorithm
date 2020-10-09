package baekjoon.p11403;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; // ������ ����
	static boolean[][] EDGE;
	static boolean[] VISIT;
	
	static void dfsFirst(int here) {
		for (int next = 0; next < N; next++) {
			if(EDGE[here][next] && !VISIT[next]) {
				dfs(next);
			}
		}
	}
	
	// ���� here���� dfs
	static void dfs(int here) {
		VISIT[here] = true;
		for (int next = 0; next < N; next++) {
			if(EDGE[here][next] && !VISIT[next]) {
				dfs(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		EDGE = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				EDGE[i][j] = st.nextToken().equals("1");
			}
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			VISIT = new boolean[N];
			dfsFirst(i);
			for (int n = 0; n < N; n++) {
				if (VISIT[n]) {bw.write("1");} 
				else {bw.write("0");}
				if (n != N - 1) {bw.write(" ");}
			}
			bw.newLine();
		}
		bw.close();
	}
	
}