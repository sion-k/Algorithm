import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		DisjointSet s = new DisjointSet(N + 1);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int com = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (com == 0) {
				s.union(a, b);
			} else if (s.find(a) == s.find(b)){
				bw.write("YES");
				bw.newLine();
			} else {
				bw.write("NO");
				bw.newLine();
			}
		}
		bw.close();
	}

}

class DisjointSet {
	int[] parent; int[] rank;

	public DisjointSet(int n) {
		parent = new int[n]; rank = new int[n];
		for (int i = 0; i < n; i++)
			parent[i] = i;
		Arrays.fill(rank, 1);
	}

	// u보다 랭크가 높은 v에 u를 밑으로 넣는다
	void union (int u, int v){
		u = find(u); v = find(v);
		if (u == v) return; // 이미 같은 집합에 속한 경우 예외 처리
		if (rank[u] > rank[v]) { // v가 rank가 더 높게 만들어 준다
			int temp = u;
			u = v; v = temp;
		}
		parent[u] = v; // v의 밑에 u를 넣는다
		// 둘이 rank가 같았다면 rank가 증가한다
		if (rank[u] == rank[v]) rank[v]++;
	}

	// u가 속한 집합을 반환
	int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}
}
