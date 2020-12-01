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
		int M = (int)(Math.random() * (int)(Math.ceil(N / 2))) + 1;
		bw.write(N + " " + M); bw.newLine();
		for (int i = 0; i < N; i++) {
			int rand = (int)(Math.random() * 32767);
			if (rand % 2 == 0) {rand = -rand;}
			bw.write(String.valueOf(rand)); bw.newLine();
		}
		bw.close();
	}

}