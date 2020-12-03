package baekjoon.p09466;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] adj;
	static boolean[] visit;
	static boolean[] check;

	// here에서 시작하는 사이클에 포함된 정점의 수 반환
	static int dfs(int here) {
		// 만약 사이클 여부를 확인하기 위한 check라면 사이클
		if (check[here]) {return 1;}
		// 그게 아니라면 방문한 지점은 패스
		if (visit[here]) {return 0;}
		visit[here] = check[here] = true;
		int cycle = 0;
		if ((cycle = dfs(adj[here])) > 0) {return 1 + cycle;}
		check[here] = false;
		return cycle;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			adj = new int[N + 1];
			visit = new boolean[N + 1];
			check = new boolean[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++)
				adj[i] = Integer.parseInt(st.nextToken());
			int sum = 0;
			for (int here = 1; here <= N; here++) {
				if (!visit[here]) {
					int t = dfs(here);
					if (t > 0) t--;
					sum += t;
					System.out.println(here + " : " + t);
					check = new boolean[N + 1];
				}
			}
			bw.write(String.valueOf(N - sum)); bw.newLine();
		}
		bw.close();
	}

}