import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] S = new int[N];
		int index = 1; Map<Integer, Integer> compress = new HashMap<>();
		ArrayList<Integer> sorted = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			sorted.add(S[i]);
		}
		// 좌표 압축
		Collections.sort(sorted);
		for (int x : sorted) if (compress.get(x) == null) compress.put(x, index++);
		for (int i = 0; i < N; i++) S[i] = compress.get(S[i]);
		FenwickTree t = new FenwickTree(N);
		for (int i = 1; i <= N; i++) t.add(i, 1);
		// P[x] = x가 S의 몇번째에 있는지 반환 (1-based), x가 여러개일 수 있으므로 작은 것 부터 
		ArrayList<PriorityQueue<Integer>> P = new ArrayList<>(N);
		for (int i = 0; i <= N; i++) P.add(new PriorityQueue<>());
		for (int i = 0; i < N; i++) P.get(S[i]).add(i + 1);
		long ret = 0;
		for (int i = 1; i < index; i++) {
			while (!P.get(i).isEmpty()) {
				int x = P.get(i).poll();
				ret += t.sum(x) - 1;
				t.add(x, -1);
			}
		}
		System.out.println(ret);
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
	
}
