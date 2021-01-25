package codeforce.r697;

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
			long N = Long.parseLong(br.readLine());
			boolean flag = false;
			if (N % 2 == 1) {
				flag = true;
			} else {
				while (N % 2 == 0) {N /= 2;}
				if (N != 1) flag = true;
			}
			bw.write(flag ? "YES" : "NO");
			bw.newLine();
		}
		bw.close();
	}

}