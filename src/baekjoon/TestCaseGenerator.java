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
		int N = 1000;
		bw.write(String.valueOf(N)); bw.newLine();
		for (int i = 1; i <= N - 1; i++) {
			bw.write(i + " " + (i + 1)); bw.newLine();
		}
		bw.close();
	}

}