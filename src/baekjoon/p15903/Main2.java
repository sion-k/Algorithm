package baekjoon.p15903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	// �� ��ü���� ī���� �� ���� (x + y)��ŭ �þ
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		// ���� ���� ���ҵ� ���� ���� �� �ֵ��� �켱���� ť ���
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		// ���� ó�� ī����� ���� ���س��´�.
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(st.nextToken());
			sum += a;
			pq.offer(a);
		}
		for (int i = 0; i < m; i++) {
			int add = (pq.poll() + pq.poll());
			pq.offer(add); pq.offer(add);
			sum += add;
		}
		System.out.println(sum);
	}

}