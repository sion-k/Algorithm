package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestCaseGenerator {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("testCase.txt"));
		br.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter("testCase.txt"));
		int N = (int)(Math.random() * (1000)) + 1;
		int M = (int)(Math.random() * (1000)) + 1;
		int INF = 100;
		bw.write(N + " " + M); bw.newLine();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int rand = (int)(Math.random() * (INF + 1));
				if (new Random().nextBoolean()) {rand = -rand;}
				bw.write(String.valueOf(rand));
				if (j != M - 1) {bw.write(" ");}
			}
			bw.newLine();
		}
		bw.close();
	}

}