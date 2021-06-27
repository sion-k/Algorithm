package codeforce.r661;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(S);
			boolean ret = true;
			for (int i = 0; i < N - 1; i++) {
				if (Math.abs(S[i] - S[i + 1]) > 1) {
					ret = false; break;
				}
			}
			bw.write(ret ? "YES" : "NO");
			bw.newLine();
		}
		bw.close();
	}

}