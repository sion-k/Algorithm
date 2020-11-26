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
		int N = 1000; int M = 1;
		int INF = 100;
		bw.write(N + " " + M); bw.newLine();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.write(String.valueOf((int)(Math.random() * (INF + 1))));
				if (j != M - 1) {bw.write(" ");}
			}
			bw.newLine();
		}
		bw.close();
	}

}