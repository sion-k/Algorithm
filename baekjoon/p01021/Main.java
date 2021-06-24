package baekjoon.p01021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		Deque<Integer> dq = new LinkedList<>();
		for (int i = 1; i <= N; i++) {dq.offer(i);}
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int sum = 0;
		for (int p = 0; p < M; p++) {
			int toPick = Integer.parseInt(st.nextToken());
			if (dq.peekFirst() == toPick) {dq.pollFirst(); continue;}
			int found = 0;
			Iterator<Integer> itr = dq.iterator();
			while (itr.hasNext() && itr.next() != toPick) {found++;}
			// 왼쪽 연산이 더 빠른 경우
			if (found <= dq.size() / 2) {
				for (int i = 0; i < found; i++) {
					dq.offerLast(dq.pollFirst());
				}
				sum += found;
				dq.pollFirst();
			} else {
				for (int i = 0; i < dq.size() - found; i++) {
					dq.offerFirst(dq.pollLast());
				}
				sum += dq.size() - found;
				dq.pollFirst();
			}
		}
		System.out.println(sum);
	}

}