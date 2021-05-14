package baekjoon.p02109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Lecture> pq = new PriorityQueue<>();
		boolean[] taken = new boolean[10001];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int price = Integer.parseInt(st.nextToken());
			int date = Integer.parseInt(st.nextToken());
			pq.offer(new Lecture(price, date));
		}
		int sum = 0;
		while (!pq.isEmpty()) {
			Lecture lec = pq.poll(); int date = lec.date;
			while (date >= 1 && taken[date]) {date--;}
			if (date >= 1) {taken[date] = true; sum += lec.price;}
		}
		System.out.println(sum);
	}

}

class Lecture implements Comparable<Lecture> {
	int price; int date;
	public Lecture(int p, int d) {price = p; date = d;}
	@Override
	public int compareTo(Lecture o) {return o.price - price;}
}