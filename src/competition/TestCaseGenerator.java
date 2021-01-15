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
		int N = 2000; int M = 2000;
		bw.write(N + " " + M); bw.newLine();
		for (int i = 0; i < M; i++) {
			bw.write((int)(Math.random() * N) + " " + (int)(Math.random() * N));
			bw.newLine();
		}
		bw.close();
	}

}