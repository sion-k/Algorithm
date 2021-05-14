package baekjoon.p02750;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static void selectionSort(int[] S) {
		for (int i = 0; i < S.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < S.length; j++) {
				minIndex = S[j] < S[minIndex] ? j : minIndex;
			}
			int temp = S[i];
			S[i] = S[minIndex];
			S[minIndex] = temp;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] S = new int[N];
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		selectionSort(S);
		
		for (int i = 0; i < N; i++) {
			bw.write(S[i] + "");
			bw.newLine();
		}
		bw.close();
	}

}
