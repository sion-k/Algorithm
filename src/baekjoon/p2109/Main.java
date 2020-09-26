package baekjoon.p2109;

import java.util.*;
import java.io.*;

public class Main {
	private static class Lecture implements Comparable<Lecture> {
		public int p;
		public int d;

		public Lecture(int p, int d) {this.p = p;this.d = d;}

		@Override
		public int compareTo(Lecture o) {
			return d == o.d ? o.p - p : o.d - d ;
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
		int date = 10000;
		boolean[] booked = new boolean[10000 + 1];
		while (!queue.isEmpty() && date >= 1) {
			if(booked[date]) {date--; continue;}
			// 마감 기한이 같은 일 중에서 가장 비싼걸 뽑는다
			Lecture l = queue.peek();
			// 현재 날짜가 뽑은 강연의 기한 내라면
			if (date <= l.d) {
				l = queue.poll();
				System.out.println(date + "날에" + l + "예약");
				sum += l.p;
				booked[date] = true;
			}
			date--;
		}
		System.out.println(sum);
	}

}
