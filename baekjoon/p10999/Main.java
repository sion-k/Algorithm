package baekjoon.p10999;

import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] S = new long[N];
		for (int i = 0; i < N; i++) S[i] = Long.parseLong(br.readLine());
		RSQ rsq = new RSQ(S);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				long d = Long.parseLong(st.nextToken());
				if (b <= c) rsq.add(b - 1, c - 1, d);
			} else {
				if (b <= c) bw.append(rsq.query(b - 1, c - 1));
				else bw.append(0);
				bw.append("\n");
			}
		}
		System.out.print(bw);
	}
	
}

class RSQ {
	int n; long[] rangeSum; long[] lazy;
	
	RSQ(long[] array) {
		n = array.length;
		rangeSum = new long[4 * n]; lazy = new long[4 * n];
		init(array, 1, 0, n - 1);
	}
	
	private long init(long[] array, int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) return rangeSum[node] = array[nodeLeft];
		int mid = (nodeLeft + nodeRight) / 2;
		return rangeSum[node] = init(array, 2 * node, nodeLeft, mid) + init(array, 2 * node + 1, mid + 1, nodeRight);
	}
		
	long query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}
	
	private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
		propagate(node, nodeLeft, nodeRight);
		if (right < nodeLeft || nodeRight < left) return 0;
		if (left <= nodeLeft && nodeRight <= right) return rangeSum[node];
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, 2 * node, nodeLeft, mid) + query(left, right, 2 * node + 1, mid + 1, nodeRight);
	}
	
	long update(int index, int val) {
		return update(index, val, 1, 0, n - 1);
	}
	
	private long update(int index, int val, int node, int nodeLeft, int nodeRight) {
		if (nodeRight < index || index < nodeLeft) return rangeSum[node];
		if (nodeLeft == nodeRight) return rangeSum[node] = val;
		int mid = (nodeLeft + nodeRight) / 2;
		return rangeSum[node] = update(index, val, 2 * node, nodeLeft, mid) + update(index, val, 2 * node + 1, mid + 1, nodeRight);
	}
	
	void add(int left, int right, long k) {
		add(left, right, k, 1, 0, n - 1);
	}
	
	private void add(int left, int right, long k, int node, int nodeLeft, int nodeRight) {
		propagate(node, nodeLeft, nodeRight);
		if (right < nodeLeft || nodeRight < left) return;
		if (left <= nodeLeft && nodeRight <= right) {
			lazy[node] += k;
			propagate(node, nodeLeft, nodeRight);
			return;
		}
		int mid = (nodeLeft + nodeRight) / 2;
		add(left, right, k, 2 * node, nodeLeft, mid);
		add(left, right, k, 2 * node + 1, mid + 1, nodeRight);
		rangeSum[node] = rangeSum[2 * node] + rangeSum[2 * node + 1];
	}
	
	private void propagate(int node, int nodeLeft, int nodeRight) {
		if (lazy[node] != 0) {
			if (nodeLeft != nodeRight) {
				lazy[2 * node] += lazy[node];
				lazy[2 * node + 1] += lazy[node];
			}
			rangeSum[node] += lazy[node] * (nodeRight - nodeLeft + 1);
			lazy[node] = 0;
		}
	}
	
}
