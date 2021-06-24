import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		// BufferedReader br = new BufferedReader(new FileReader("testCase.txt"));
		
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N + 1];
		long[] R = new long[N + 1];
		ArrayList<Pair> P = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			P.add(new Pair(S[i], i));
		}
		Collections.sort(P);
		FenwickTree t = new FenwickTree(N);
		for (int i = 1; i <= N; i++) t.add(i, 1);
		long time = 0; int done = 0; int pro = 0;
		for (int i = 0; i < N; i++) {
			Pair p = P.get(i);
			R[p.index] = time + (N - done) * (long)(p.x - pro - 1) + t.sum(p.index - 1) + 1;
			ArrayList<Integer> toRemoved = new ArrayList<>();
			toRemoved.add(p.index);
			while (i + 1 < N && Integer.compare(p.x, P.get(i + 1).x) == 0) {
				i++;
				R[P.get(i).index] = R[p.index] + t.sum(P.get(i).index) - t.sum(p.index);
				toRemoved.add(P.get(i).index);
			}
			time += (p.x - pro) * (long)(N - done); done += toRemoved.size(); pro += (p.x - pro);
			for (int r : toRemoved) t.add(r, -1);				
		}
		for (int i = 1; i <= N; i++) bw.append(R[i]).append("\n");
		System.out.print(bw);
	}
	
}

class Pair implements Comparable<Pair> {
	int x, index;
	
	Pair(int x, int i) { this.x = x; this.index = i; }
	
	@Override
	public int compareTo(Pair o) { return x == o.x ? index - o.index : x - o.x; }
	
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
	
}
