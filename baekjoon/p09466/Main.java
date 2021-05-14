package baekjoon.p09466;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] adj;
	static boolean[] currentVisit; // ���� ����ǰ� �ִ� DFS�Լ��� �湮�ߴ��� ����
	static boolean[] visit; // ������ DFSȣ�⿡�� ������ �湮�ߴ��� ����
	
	static int cycle; // ����Ŭ�� �̷�� ����� ����
	
	static void dfs(int here) {
		currentVisit[here] = true;
		int there = adj[here];
		// ������ DFS�� ȣ�⿡�� �湮���� ���� ���� ������ �湮�Ѵ�
		// ������ �湮�� �������� ����Ŭ�� ���Եǰų� ���Ե��� �ʱ� ������ Ȯ������ �ʿ䰡 ����
		if (!visit[there]) {
			if (!currentVisit[there]) {
				dfs(there);
			} else {
				cycle++;
				// ����Ŭ�� �̷�� ������ ������ ����(������ ������ ����� ����)
				for (int i = there; i != here; i = adj[i]) cycle++;
			}
		}
		visit[here] = true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			adj = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) adj[i] = Integer.parseInt(st.nextToken());
			currentVisit = new boolean[N + 1];
			visit = new boolean[N + 1];
			cycle = 0;
			for (int here = 1; here <= N; here++)
				if (!visit[here]) dfs(here);
			System.out.println(N - cycle);
		}
	}

}