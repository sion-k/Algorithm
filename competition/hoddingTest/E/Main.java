package competition.hoddingTest.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Pair>> P;
	
	// ���� ������ �ִ� �ݾ��� C�϶� �ʺ� x������ ��, ���̸� �÷��� ���� �� �ִ� �ִ� �Ƹ��ٿ� ����
	static long bin(int x, int C) {
		    
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		// P[i] = x��ǥ�� i�� �������� y��ǥ ������ �������� ������ �迭 
		P = new ArrayList<>(100001);
		for (int i = 0; i <= 100000; i++) P.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			P.get(X).add(new Pair(Y, V));
		}
		for (int i = 0; i <= 100000; i++) Collections.sort(P.get(i));
		
	}
	
}
class Pair implements Comparable<Pair> {
	int y; int v;
	public Pair(int y, int v) {this.y = y; this.v = v;}
	@Override
	public int compareTo(Pair o) {return y - o.y;}
}