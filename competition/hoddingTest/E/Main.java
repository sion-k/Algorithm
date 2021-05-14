package competition.hoddingTest.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Pair>> P;
	
	// 현재 가지고 있는 금액이 C일때 너비가 x까지일 때, 높이를 늘려서 얻을 수 있는 최대 아름다운 정도
	static long bin(int x, int C) {
		    
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		// P[i] = x좌표가 i인 광물들을 y좌표 순으로 오름차순 정렬한 배열 
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