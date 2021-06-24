package baekjoon.p03653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] S = new int[M]; // 보려고 하는 영화
			int[] P = new int[N]; // 현재 가지고 있는 i번째 영화의 위치
			for (int i = 0; i < N; i++) P[i] = M + i;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) S[i] = Integer.parseInt(st.nextToken());
			// 구간 노드를 [0, M) + [M, M + N)으로 정의한다
			FenwickTree t = new FenwickTree(N + M);
			for (int i = 0; i < N; i++) t.add(M + i, 1);
			int head = M - 1;
			for (int i = 0; i < M; i++) {
				ans.append(t.sum(P[S[i] - 1]) - 1);
				t.add(P[S[i] - 1], -1);
				P[S[i] - 1] = head;
				t.add(head--, 1);
				if (i != M - 1) ans.append(" ");
			}
			ans.append("\n");
		}
		System.out.print(ans);
	}
	
}

class FenwickTree {
	int[] tree;
	
	public FenwickTree(int n) { tree = new int[n + 1]; }
	
	void add(int pos, int val) {
		pos++;
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
	
	int sum(int pos) {
		pos++;
		int ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos -= (pos & -pos);
		}
		return ret;
	}
	
}
