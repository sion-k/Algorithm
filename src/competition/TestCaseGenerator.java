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
		int T = 1; int MAX_F = 100000;
		bw.write(String.valueOf(T)); bw.newLine();
		for (int c = 0; c < T; c++) {
			bw.write(String.valueOf(MAX_F)); bw.newLine();
			for (int f = 0; f < 2 * MAX_F - 1; f += 2) {
				bw.write((f + 1) + " " + (f + 2));
				bw.newLine();
			}
		}
		bw.close();
	}

}