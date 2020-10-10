package baekjoon.p08958;

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
			int score = 0;
			String quiz = br.readLine();
			int ds = 1;
			for (int i = 0; i < quiz.length(); i++) {
				if (quiz.charAt(i) == 'O') {
					score += ds;
					ds++;
				} else {ds = 1;}
			}
			bw.write(String.valueOf(score));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}