import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());
		int[] adj = new int[1000001];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) adj[Integer.parseInt(st.nextToken())] = i;
		FenwickTree t = new FenwickTree(N); // i번째에 위치한 기계까지 연결된 케이블 끝의 개수
		long sum = 0;
		for (int i = 1; i <= N; i++) {
			int there = adj[S[i]];
			sum += (t.sum(N) - t.sum(there));
			t.add(there, t.sum(there) - t.sum(there - 1) + 1);
		}
		System.out.println(sum);
	}
	
}

class FenwickTree {
	int[] tree;
	
	public FenwickTree(int n) { tree = new int[n + 1]; }
	
	void add(int pos, int val) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
	
	int sum(int pos) {
		int sum = 0;
		while (pos > 0) {
			sum += tree[pos];
			pos -= (pos & -pos);
		}
		return sum;
	}
	
}
