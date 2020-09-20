package baekjoon.p1202;

import java.io.*;
import java.util.*;

public class Main {

	private static class Jewelry implements Comparable<Jewelry> {
		public int M;
		public int V;

		public Jewelry(int M, int V) {
			this.M = M;
			this.V = V;
		}

		public int compareTo(Jewelry other) {
			return M - other.M;
		}

		@Override
		public String toString() {
			return M + " " + V;
		}
	}

	private static class ValueOrder implements Comparator<Jewelry> {
		public int compare(Jewelry j1, Jewelry j2) {
			return j2.V - j1.V;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Jewelry[] J = new Jewelry[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			J[i] = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int[] bagCap = new int[K];
		for (int i = 0; i < K; i++) {
			bagCap[i] = Integer.parseInt(br.readLine());
		}
		br.close();

		Arrays.sort(J);
		Arrays.sort(bagCap);

		Queue<Jewelry> queue = new PriorityQueue<>(N, new ValueOrder());

		long maxValue = 0;
		int i = 0;
		for (int b = 0; b < K; b++) {
			while (i < N && J[i].M <= bagCap[b]) {
				queue.offer(J[i]);
				i++;
			}
			if (!queue.isEmpty()) {
				maxValue += queue.poll().V;
			}
		}

		System.out.println(maxValue);
	}

}
