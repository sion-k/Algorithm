package codeforce.c640.A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int temp = N;
			int cnt = 0;
			while (N != 0) {
				if (N % 10 != 0) {cnt++;}
				N = N / 10;
			}
			N = temp;
			bw.write(String.valueOf(cnt));
			bw.newLine();
			int fac = 1;
			while (N != 0) {
				int d = (N % 10) * fac;
				if (d != 0) {
					bw.write(String.valueOf(d));
				}
				fac = fac * 10;
				N = N / 10;
				if (d != 0 && N != 0) {bw.write(" ");}
			}
			bw.newLine();
		}
		bw.close();
	}

}