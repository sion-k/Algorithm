package baekjoon.p05012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		// P[x] = S�� ���� x�� ��ġ (���� ������)  
		ArrayList<PriorityQueue<Integer>> P = new ArrayList<>();
		for (int i = 0; i <= N; i++) P.add(new PriorityQueue<>());
		for (int i = 0; i < N; i++) P.get(S[i]).add(i + 1);
		long[] I = new long[N + 1]; // S[i]�� ������ �ִ� Inversion�� ���� ����
		FenwickTree t = new FenwickTree(N); // S[i]�� �����ϴ��� ����
		for (int i = 1; i <= N; i++) t.add(i, 1);
		for (int i = 1; i <= N; i++) {
			PriorityQueue<Integer> pq = P.get(i);
			PriorityQueue<Integer> temp = new PriorityQueue<>();
			while (!pq.isEmpty()) {
				int x = pq.poll();
				I[x] = t.sum(x) - 1;
				t.add(x, -1);
				temp.offer(x);
			}
			P.set(i, temp);
		}
		long sum = 0;
		t = new FenwickTree(N);
		for (int i = 1; i <= N; i++) t.add(i, I[i]);
		for (PriorityQueue<Integer> pq : P) {
			while (!pq.isEmpty()) {
				int x = pq.poll();
				sum += t.sum(x - 1);
				t.add(x, -(t.sum(x) - t.sum(x - 1)));
			}
		}
		System.out.println(sum);
	}
	
}

class FenwickTree {
	long[] tree;
	
	FenwickTree(int n) { tree = new long[n + 1]; }
	
	void add(int pos, long val) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
	
	long sum(int pos) {
		long ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos -= (pos & -pos);
		}
		return ret;
	}
	
}
