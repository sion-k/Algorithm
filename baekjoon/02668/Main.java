import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] adj;
	static boolean[] visit;

	static int DFS(int here, int start) {
		visit[here] = true;
		int there = adj[here];
		int sum = 1;
		if (!visit[there]) {
			int cnt = DFS(there, start);
			if (cnt == 0) {sum = 0;}
			else {sum += cnt;}
		} else if (there != start) {
			sum = 0;
		}
		if (sum == 0) {visit[here] = false;}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		adj = new int[N + 1]; visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = Integer.parseInt(br.readLine());
		}
		int sum = 0;
		for (int i = 1; i <= N; i++)
			if (!visit[i]) sum += DFS(i, i);
		bw.write(String.valueOf(sum));bw.newLine();
		for (int i = 1; i <= N; i++) {
			if (visit[i]) {
				bw.write(String.valueOf(i));
				bw.newLine();
			}
		}
		bw.close();
	}

}
