package baekjoon.p15650;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<Integer> S = new ArrayList<>();
	
	static void print() throws IOException {
		for(int i = 0; i < S.size(); i++) {
			bw.write(String.valueOf(S.get(i)));
			if (i != S.size() - 1) {bw.write(" ");}
		}
		bw.newLine();
	}
	
	// 맨 마지막으로 lastPick을 골랐을 때 N개에서 M개를 고르는 수열 모두 출력
	// [1, N] 자연수, 오름차순
	static void pick(int N , int M, int lastPick) throws IOException {
		if (M == 0) {print(); return;}
		
		for (int pick = lastPick + 1; pick <= N; pick++) {
			S.add(pick);
			pick(N, M - 1, pick);
			S.remove(S.size() - 1);
		}
	}
	
//	static void pick(int N , int M) throws IOException {
//		if (M == 0) {print(); return;}
//		int lastPick = S.size() == 0 ? 0 : S.size() - 1;
//		for (int pick = lastPick + 1; pick <= N; pick++) {
//			S.add(pick);
//			pick(N, M - 1);
//			S.remove(S.size() - 1);
//		}
//	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); int M = sc.nextInt();
		sc.close();
		pick(N, M, 0);
		bw.close();
	}

}
