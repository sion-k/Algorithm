package baekjoon.p16437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static ArrayList<ArrayList<Integer>> children;
	static int[] amount;
	
	// here을 루트로 하는 서브트리에서 루트에 도착하고 살아남는 양의 수
	static long inorder(int here) {
		long sum = amount[here];
		for (int ch : children.get(here)) sum += inorder(ch);
		return Math.max(sum, 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N + 1]; parent[1] = 1;
		children = new ArrayList<>(N + 1);
		for (int i = 0; i < N + 1; i++) children.add(new ArrayList<>());
		amount = new int[N + 1];
		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String t = st.nextToken();
			amount[i] = Integer.parseInt(st.nextToken());
			if (t.equals("W")) amount[i] *= -1;
			int p = Integer.parseInt(st.nextToken());
			parent[i] = p;
			children.get(p).add(i);
		}
		System.out.println(inorder(1));
	}
	
}