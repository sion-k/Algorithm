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
		int N = 5000; int M = 1;
		bw.write(N + " " + M); bw.newLine();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				bw.write(String.valueOf((int)(Math.random() * 1000) + 1));
			bw.newLine();
		}
		bw.write("0 0");
		bw.close();
	}

}