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
		int N = 500; int M = 500;
		bw.write(N + " " + M); bw.newLine();
		bw.write(N + " ");
		for (int j = 1; j <= 498; j++)
			bw.write("0 ");
		bw.write(String.valueOf(N));
		bw.close();
	}

}