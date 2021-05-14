package algospot.TRAVERSAL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int root;
	static int[] pre; static int[] in;

	static StringBuilder ans;

	static void rec(int i, int j) {
		if (i == j) {return;}
		int k = 0;
		for (int n = 0; n < N; n++) {
			if (pre[root] == in[n]) {k = n; break;}
		}
		root++;
		rec(i, k); rec(k + 1, j);
		ans.append(in[k]); ans.append(" ");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			N = Integer.parseInt(br.readLine());
			pre = new int[N]; in = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				pre[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				in[i] = Integer.parseInt(st.nextToken());
			}
			root = 0;
			ans = new StringBuilder();
			rec(0, N);
			bw.write(ans.toString().trim());
			bw.newLine();
		}
		bw.close();
	}

}