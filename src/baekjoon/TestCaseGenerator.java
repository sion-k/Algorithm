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
		//bw.write("1000000"); bw.newLine();
		for (int w = 0; w < 4000; w++) {
			bw.write(String.valueOf(new Random().nextInt(26) + 'A'));
		}
		bw.newLine();
		for (int w = 0; w < 4000; w++) {
			bw.write(String.valueOf(new Random().nextInt(26) + 'A'));
		}
		bw.newLine();
		bw.close();
	}

}