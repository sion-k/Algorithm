package codeforce.r693;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class F {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
			}
			if ((2 * N - M) % 2 != 0) {
				bw.write("NO");
				bw.newLine();
				continue;
			}
		}
		bw.close();
	}

}
