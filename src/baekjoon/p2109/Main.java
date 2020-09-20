package baekjoon.p2109;

import java.util.*;
import java.io.*;

public class Main {
	private static class Lecture implements Comparable<Lecture> {
		public int p;
		public int d;

		public Lecture(int p, int d) {
			this.p = p;
			this.d = d;
		}

		@Override
		public int compareTo(Lecture o) {
			return p == o.p ? d - o.d : o.p - p;
		}

		@Override
		public String toString() {
			return p + " " + d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Queue<Lecture> queue = new PriorityQueue<>();
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			queue.offer(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		System.out.println(queue);
		int sum = 0;
		int d = 1;
		while (!queue.isEmpty() && d <= 10000) {
			Lecture l = queue.poll();
			if (d <= l.d) {
				sum += l.p;
				d++;
			}
		}
		System.out.println(sum);
	}

}
