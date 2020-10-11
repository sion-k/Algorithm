package baekjoon.p16510;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] prefixSum = new int[N]; // i번째 원소 까지의 합을 저장
		st = new StringTokenizer(br.readLine(), " ");
		int tempSum = 0;
		for (int i = 0; i < N; i++) {
			prefixSum[i] = (tempSum += Integer.parseInt(st.nextToken()));
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < M; i++) {
			int index = Arrays.binarySearch(prefixSum, Integer.parseInt(br.readLine()));
			if (index < 0) {index = -index - 1;}
			else {index += 1;}
			bw.write(String.valueOf(index));
			bw.newLine();
		}
		br.close();
		bw.close();		
		
	}

}