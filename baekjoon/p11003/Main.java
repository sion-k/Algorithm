package baekjoon.p11003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		Pair[] P = new Pair[N];
		for (int i = 0; i < N; i++) P[i] = new Pair(i, Integer.parseInt(st.nextToken()));
		Deque<Pair> dq = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			while (!dq.isEmpty() && dq.peekLast().x > P[i].x) dq.pollLast();
			dq.offerLast(P[i]);
			while (!dq.isEmpty() && dq.peekFirst().i < i - L + 1) dq.pollFirst();
			bw.append(dq.peekFirst().x).append(" ");
		}
		System.out.println(bw.toString().trim());
	}
	
}

class Pair implements Comparable<Pair> {
	int i, x;
	
	Pair(int i, int x) { this.i = i; this.x = x; }
	
	@Override
	public int compareTo(Pair o) { return x - o.x; }
	
	public String toString() { return String.format("(%d, %d)", i, x).toString(); }
	
}
