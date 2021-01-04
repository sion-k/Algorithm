package codeforce.r693;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int cnt = 1;
			while (w % 2 == 0) {
				w /= 2;
				cnt *= 2;
			}
			while (h % 2 == 0) {
				h /= 2;
				cnt *= 2;
			}
			bw.write(cnt >= n ? "YES" : "NO");
			bw.newLine();
		}
		bw.close();
	}

}