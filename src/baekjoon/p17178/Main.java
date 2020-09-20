package baekjoon.p17178;

import java.io.*;
import java.util.*;

public class Main {
	private static class Ticket implements Comparable<Ticket> {
		private char code;
		private int num;

		public Ticket(char c, int n) {
			code = c;
			num = n;
		}

		@Override
		public int compareTo(Ticket t) {
			if (t == null)
				return -1;
			return code == t.code ? num - t.num : (int) (code - t.code);
		}

		@Override
		public String toString() {
			return code + "-" + num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Queue<Ticket> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				String[] e = st.nextToken().split("\\-");
				queue.add(new Ticket(e[0].charAt(0), Integer.parseInt(e[1])));
			}
		}
		br.close();
		Stack<Ticket> stack = new Stack<>();
		Ticket s = null;
		Ticket q = null;
		Ticket last = new Ticket('A', 1);
		while ((q = queue.peek()) != null) {
			if (stack.empty()) {
				s = null;
			} else {
				s = stack.peek();
			}
			if (s == null || q.compareTo(s) < 0) {
				if (q.compareTo(Collections.min(queue)) == 0) {
					if (q.compareTo(last) >= 0) {
						last = queue.poll();
					} else {
						System.out.println("BAD");
						return;
					}
				} else {
					stack.push(queue.poll());
				}
			} else {
				last = stack.pop();
			}
		}
		System.out.println("GOOD");
	}
}
