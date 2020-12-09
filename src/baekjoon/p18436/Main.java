package baekjoon.p18436;

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
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		FenwickTree t = new FenwickTree(N);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int odd = Integer.parseInt(st.nextToken()) % 2;
			if (odd == 1) {t.add(i, 1);}
			S[i] = odd;
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int com = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (com == 1) {
				if (S[a - 1] % 2 != b % 2) {
					t.add(a - 1, b % 2 - S[a - 1] % 2);
				}
			} else {
				int oddNum = t.sum(b - 1) - t.sum(a - 2);
				if (com == 2) {
					int length = b - a + 1;
					bw.write(String.valueOf(length - oddNum));
				} else {
					bw.write(String.valueOf(oddNum));
				}
				bw.newLine();
			}
		}
		bw.close();
	}

}

// A[, i]까지의 홀수의 개수의 합을 저장
class FenwickTree {
	int[] tree;

	public FenwickTree(int n) {tree = new int[n + 1];}

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
			pos &= (pos - 1);
		}
		return ret;
	}
}