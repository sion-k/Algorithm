package baekjoon.p01280;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// Range Count Query [0, pos]에 존재하는 좌표의 수를 답한다
		FenwickTree rcq = new FenwickTree(200001); 
		// Range Sum Query [0, pos]에 존재하는 좌표값들의 합을 답한다
		FenwickTree rsq = new FenwickTree(200001);
		int ret = 1;
		int x = Integer.parseInt(br.readLine());
		rcq.add(x, 1); rsq.add(x, x);
		for (int i = 2; i <= N; i++) {
			x = Integer.parseInt(br.readLine());
			// [0, x]
			long temp = (rcq.sum(x) * x - rsq.sum(x) + MOD) % MOD;
			// [x, 200000]
			temp = (temp + (rsq.sum(200000) - rsq.sum(x)) - (rcq.sum(200000) - rcq.sum(x)) * x + MOD) % MOD;
			rcq.add(x, 1); rsq.add(x, x);
			ret = (int)((ret * temp) % MOD);
		}
		System.out.println(ret);
	}
	
}

//펜윅 트리
class FenwickTree {
	long[] tree;
	public FenwickTree(int n) {tree = new long[n + 1];}

	void add(int pos, int val) {
		// 내부적으로는 1-based
		pos++;
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}

	long sum(int pos) {
		// 내부적으로는 1-based
		pos++;
		long ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos &= (pos - 1);
		}
		return ret;
	}

}