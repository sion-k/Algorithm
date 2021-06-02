package baekjoon.p05419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Pair> S = new ArrayList<>(N); 
			ArrayList<Integer> yLoc = new ArrayList<>(N); 
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");				
				S.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				yLoc.add(S.get(i).y);
			}
			Collections.sort(S);
			// ÁÂÇ¥ ¾ÐÃà
			Collections.sort(yLoc);
			Map<Integer, Integer> compress = new HashMap<>();
			int index = 1;
			for (int y : yLoc) if (compress.get(y) == null) compress.put(y, index++);
			FenwickTree t = new FenwickTree(index - 1);
			long cnt = 0; int n = 0;
			for (Pair p : S) {
				p.y = compress.get(p.y);
				cnt += n - t.sum(p.y - 1);
				t.add(p.y, 1);
				n++;
			}
			bw.append(cnt).append("\n");
		}
		System.out.print(bw);
	}
	
}

class Pair implements Comparable<Pair> {
	int x, y;
	Pair(int x, int y) { this.x = x; this.y = y; }
	
	public int compareTo(Pair o) { return x == o.x ? o.y - y : x - o.x; }
	
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
