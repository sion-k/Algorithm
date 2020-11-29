package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestCaseGenerator {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("testCase.txt"));
		br.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter("testCase.txt"));
		int N = 100;
		int M = 100;
		int L = 10;
		int G = 1000000;
		int INF = 1000;
		int T = 10;
		bw.write(String.valueOf(T)); bw.newLine();
		for (int c = 0; c < T; c++) {
			bw.write(N + " " + M + " " + L + " " + G); bw.newLine();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M - 1; j++) {
					int rand = (int)(Math.random() * INF) + 1;
					bw.write(String.valueOf(rand));
					if (j != M - 2) {bw.write(" ");}
				}
				bw.newLine();
			}
			for (int i = 0; i < N - 1; i++) {
				for (int j = 0; j < M; j++) {
					int rand = (int)(Math.random() * INF) + 1;
					bw.write(String.valueOf(rand));
					if (j != M - 1) {bw.write(" ");}
				}
				bw.newLine();
			}
		}
		bw.close();
	}

}