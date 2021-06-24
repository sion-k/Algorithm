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
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 0) {
				long sum =
				t.sum(Math.max(b, c) - 1) - t.sum(Math.min(b, c) - 2);
				bw.write(String.valueOf(sum));
				bw.newLine();
			} else {
				int diff = c - (int)(t.sum(b - 1) - t.sum(b - 2));
				t.add(b - 1, diff);
			}
		}
		bw.close();
	}

}

class FenwickTree {
	long[] tree; // S[i]를 구간의 오른쪽 끝으로 하는 구간 합

	public FenwickTree(int n) {tree = new long[n + 1];}

	// S[0, pos]까지의 부분 합 반환
	long sum(int pos) {
		pos++; // 1-based
		long ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos &= (pos - 1);
		}
		return ret;
	}

	void add(int pos, int val) {
		pos++; // 1-based
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
}
