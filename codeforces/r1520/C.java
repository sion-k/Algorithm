package codeforce.r1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class C {
	static final String[] example = {"", "1", "-1", "2 9 7\n4 6 3\n1 8 5"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			if (N <= 3) System.out.println(example[N]);
			else {
				Deque<Integer> dq = new LinkedList<>();
				dq.offer(2); dq.offer(4); dq.offer(1); dq.offer(3);
				for (int i = 0; i < N - 4; i++) {
					if (i % 2 == 0) dq.offerFirst(i + 5);
					else dq.offerLast(i + 5);
				}
				for (int i = 0; i < N; i++) {
					StringBuilder ans = new StringBuilder();
					for (int x : dq) ans.append(x + N * i).append(" ");
					System.out.println(ans.toString().trim());
				}
			}
		}
	}
	
}