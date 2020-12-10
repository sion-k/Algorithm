package baekjoon.p10991;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < N - i; j++)
				bw.write(" ");
			for (int j = 0; j < 2 * i - 1; j++) {
				if (j % 2 == 0)
					bw.write("*");
				else
					bw.write(" ");
			}
			bw.newLine();
		}
		bw.close();
	}

}