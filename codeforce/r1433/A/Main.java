package codeforce.r677.A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			String line = br.readLine();
			int digit = line.length();
			int ret = ((line.charAt(0) - '0') - 1) * 10;
			for (int i = 1; i <= digit; i++) {ret += i;}
			bw.write(String.valueOf(ret));
			bw.newLine();
		}
		bw.close();
	}

}