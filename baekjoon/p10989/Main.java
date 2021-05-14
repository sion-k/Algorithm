package baekjoon.p10989;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// [1, 10000], 0번째 인덱스는 미사용
		int[] count = new int[10001];
		for (int i = 0; i < N; i++) {
			count[Integer.parseInt(br.readLine())]++;
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i < count.length; i++) {
			for (int j = 0; j < count[i]; j++) {
				bw.write(String.valueOf(i));
				bw.newLine();
			}
		}
		bw.close();
	}

}