package baekjoon.p02243;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		FenwickTree t = new FenwickTree();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (A == 1) {
				int x = t.kth(B);
				ans.append(x).append("\n");
				t.add(x, -1);
			} else {
				t.add(B, Integer.parseInt(st.nextToken()));
			}
		}
		System.out.print(ans);
	}

}

class FenwickTree {
	int[] tree = new int[1000001];
	
	void add(int pos, int val) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
	
	int sum(int pos) {
		int ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos -= (pos & -pos);
		}
		return ret;
	}
	
	// k번째 값을 반환
	int kth(int k) {
		// f(x) = x 맛 이하의 개수
		// f(lo) < k && f(hi) >= k인 hi를 반환
		int lo = 0; int hi = 1000000;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (mid != 0 && sum(mid) >= k) hi = mid;
			else lo = mid;
		}
		return hi;
	}
	
}
