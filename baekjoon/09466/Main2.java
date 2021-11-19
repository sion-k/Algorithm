import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] adj;

	// visit[i] = -1 초기값 
	// 0 사이클에 속하지 않음, 1 사이클에 속함
	static void check(int start, int[] visit) {
		int here = start;
		ArrayList<Integer> path = new ArrayList<>();
		while (visit[here] == -1) {
			path.add(here);
			visit[here] = 2;
			here = adj[here];
		}
		int end = here;
		int flag = visit[end];
		for (int p : path) visit[p] = 0;
		if (flag == 2) {
			here = end;
			while (true) {
				visit[here] = 1;
				here = adj[here];
				if (here == end) break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			N = Integer.parseInt(br.readLine());
			adj = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) adj[i] = Integer.parseInt(st.nextToken());
			int[] visit = new int[N + 1];
			Arrays.fill(visit, -1);
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (visit[i] == -1) check(i, visit);
				if (visit[i] == 1) cnt++;
			}
			bw.append(N - cnt).append("\n");
		}
		System.out.print(bw);
	}

}
