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
		bw.write("10 10 1000"); bw.newLine();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				bw.write('a');
			}
			bw.newLine();
		}
		for (int w = 0; w < 1000; w++) {
			for (int i = 0; i < 5; i++) {
				bw.write('a');
			}
			bw.newLine();
		}
		bw.close();
	}

}