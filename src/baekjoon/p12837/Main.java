package baekjoon.p12837;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		FenwickTree t = new FenwickTree(N);
		int Q = Integer.parseInt(st.nextToken());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int com = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			if (com == 1) {
				t.add(p - 1, q);
			} else {
				long diff = t.sum(q - 1) - t.sum(p - 2);
				bw.write(String.valueOf(diff));
				bw.newLine();
			}
		}
		bw.close();
	}

}
class FenwickTree {
	long[] tree;
	public FenwickTree(int n) {tree = new long[n + 1];}

	void add(int pos, int val) {
		pos++;
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}

	long sum(int pos) {
		pos++;
		long ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos &= (pos - 1);
		}
		return ret;
	}
}