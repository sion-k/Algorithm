package baekjoon.p01572;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] S = new int[N];
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(br.readLine());
		long sum = 0;
		FenwickTree t = new FenwickTree();
		t.K = K;
		for (int i = 0; i < K; i++) t.add(S[i], 1);
		for (int i = 0; i < N - K + 1; i++) {
			sum += t.kth();
			t.add(S[i], -1);
			if (i + K < N) t.add(S[i + K], 1);
		}
		System.out.println(sum);
	}
	
}
class FenwickTree {
	int[] tree;
	int K;
	// 내부적으로 [0, 65536] -> [1, 65537]에 대응
	public FenwickTree() { tree = new int[65538]; }
	
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
	
	int kth() {
		// f(x) = 구간 트리 내의 x이하의 원소의 개수, K는 수열의 길이
		// f(lo) < (K + 1) / 2, f(hi) >= (K + 1) / 2인 hi반환
		int lo = -1; int hi = 65536;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (sum(mid) >= (K + 1) / 2) hi = mid;
			else lo = mid;
		}
		return hi;
	}
}