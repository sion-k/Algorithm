package baekjoon.p15903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	// 매 합체마다 카드의 총 합은 (x + y)만큼 늘어남
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		// 가장 작은 원소들 부터 뽑을 수 있도록 우선순위 큐 사용
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		// 제일 처음 카드들의 합을 구해놓는다.
		long sum = 0;
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(st.nextToken());
			sum += a;
			pq.offer((long)a);
		}
		for (int i = 0; i < m; i++) {
			long add = (pq.poll() + pq.poll());
			pq.offer(add); pq.offer(add);
			sum += add;
		}
		System.out.println(sum);
	}

}