package baekjoon.p02751;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// [0, 0] 0
		// [1, 1,000,000] 양수
		// [1,000,001, 2,000,000] 음수
		boolean[] count = new boolean[2000000 + 1];
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			if (temp >= 0) {
				count[temp] = true;
			} else {
				count[Math.abs(temp) + 1000000] = true;
			}
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 2_000_000; i >= 1_000_001; i--) {
			if(count[i]) {
				bw.write(String.valueOf(i * -1 + 1000000));
				bw.newLine();
			}
		}
		if(count[0]) {
			bw.write("0");
			bw.newLine();
		}
		for(int i = 1; i <= 1_000_000; i++) {
			if(count[i]) {
				bw.write(String.valueOf(i));
				bw.newLine();
			}
		}
		bw.close();
	}
	
}