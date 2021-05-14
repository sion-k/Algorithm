package codeforce.r677.C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int max = 1; int maxIndex = 1;
			int min = 1000000000;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				int s = Integer.parseInt(st.nextToken());
				if (s > max) {max = s; maxIndex = i;}
				if (s < min) {min = s;}
			}
			int ret = 0;
			if (min == max) {
				ret = -1;
			} else {
				ret = maxIndex;
			}
			/*
			 * ¹Ý·Ê
			 * 1
			 * 5
			 * 3 3 2 1 3
			 * wrong answer : 1
			 */
			bw.write(String.valueOf(ret));
			bw.newLine();
		}
		bw.close();
	}

}