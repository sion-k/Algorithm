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
		StringBuilder sb = new StringBuilder();
		int score = 0; int ds = 1; String quiz = "";
		for (int c = 0; c < T; c++) {
			score = 0; ds = 1;
			quiz = br.readLine();
			for (int i = 0; i < quiz.length(); i++) {
				if (quiz.charAt(i) == 'O') {
					score += (ds++);
				} else {ds = 1;}
			}
			sb.append(score).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}

}