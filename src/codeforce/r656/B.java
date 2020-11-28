package codeforce.r656;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[2 * N];
			boolean[] visit = new boolean[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 2 * N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
				if (!visit[S[i]]) {
					visit[S[i]] = true;
					bw.write(String.valueOf(S[i]));
					bw.write(" ");
				}
			}
			bw.newLine();
		}
		bw.close();
	}

}