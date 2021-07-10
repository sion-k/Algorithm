package codeforce.r674;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int sum = 1; int max = 1; int move = 0;
			while (sum < N) {
				if (sum + 1 >= sum + max) {
					sum++; max++;
				} else {
					sum += max;
				}
				move++;
			}
			bw.write(String.valueOf(move));
			bw.newLine();
		}
		bw.close();
	}

}