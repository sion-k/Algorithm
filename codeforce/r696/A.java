package codeforce.r696;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] b = new int[N];
			String line = br.readLine();
			for (int i = 0; i < N; i++)
				b[i] = line.charAt(i) - '0';
			int prev = 0;
			int[] a = new int[N];
			for (int i = 0; i < N; i++) {
				if (prev == 0) {
					a[i] = 1;
				} else if (prev == 1) {
					if (b[i] == 0) {
						a[i] = 0;
					} else {
						a[i] = 1;
					}
				} else if (prev == 2) {
					if (b[i] == 0) {
						a[i] = 1;
					} else {
						a[i] = 0;
					}
				}
				prev = a[i] + b[i];
			}
			for (int i = 0; i < N; i++)
				bw.write(String.valueOf(a[i]));
			bw.newLine();
		}
		bw.close();
	}

}