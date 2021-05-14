package baekjoon.p15652;

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
	
	// �� ���������� lastPick�� ����� �� N������ M���� ���� ���� ��� ���
	// [1, N] �ڿ���, �ߺ� ���, �񳻸�����
	static void pick(int N , int M, int lastPick) throws IOException {
		if (M == 0) {print(); return;}
		
		for (int pick = lastPick; pick <= N; pick++) {
			S.add(pick);
			pick(N, M - 1, pick);
			S.remove(S.size() - 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); int M = sc.nextInt();
		sc.close();
		pick(N, M, 1);
		bw.close();
	}

}
