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

	// here���� �����ϴ� ����Ŭ�� ���Ե� ������ �� ��ȯ
	static int dfs(int here) {
		// ���� ����Ŭ ���θ� Ȯ���ϱ� ���� check��� ����Ŭ
		if (check[here]) {return 1;}
		// �װ� �ƴ϶�� �湮�� ������ �н�
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