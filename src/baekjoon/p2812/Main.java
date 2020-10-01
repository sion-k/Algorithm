package baekjoon.p2812;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		char[] temp = br.readLine().toCharArray();
		br.close();
		LinkedList<Integer> S = new LinkedList<>();
		for(int i = 0; i < N; i++) {S.add(temp[i] - '0');}
		
		for (int i = 0; i < S.size() - 1 && K > 0; i++) {
			if (S.get(i) < S.get(i + 1)) {
				S.remove(i);
				i--;
				K--;
			}
		}
		
		for (int i = 0; i < K; i++) {S.remove(S.size() - 1);}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i : S) {bw.write(String.valueOf(i));}
		bw.newLine();
		bw.close();
	}

}
