package baekjoon.p10995;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append("*");
			if (i != N - 1) {sb.append(" ");}
		}
		String line = sb.toString();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 1) {bw.write(" ");}
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

}