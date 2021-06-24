import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		FenwickTree t = new FenwickTree(N);
		for (int i = 0; i < N; i++) t.add(i + 1, S[i]);
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int o = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			if (o == 1) {
				t.add(a, Integer.parseInt(st.nextToken()));
			} else {
				bw.append(t.kth(a)).append("\n");
			}
		}
		System.out.print(bw);
	}
	
}

class FenwickTree {
	int[] tree;
	
	FenwickTree(int n) { tree = new int[n + 1]; }
	
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
	
	// k번째 원소의 위치를 반환
	int kth(int k) {
		// sum[lo] < k && sum[hi] >= k인 hi를 반환
		int lo = 0; int hi = tree.length - 1;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (mid != 0 && sum(mid) >= k) hi = mid;
			else lo = mid;
		}
		return hi;
	}
	
}
