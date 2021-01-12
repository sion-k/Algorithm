package competition;

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
		int N = 500; int M = 500; int B = 64000000;
		bw.write(N + " " + M + " " + B); bw.newLine();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				bw.write(((int)(Math.random() * 256) + 1) + " ");
			bw.newLine();
		}
		bw.close();
	}

}