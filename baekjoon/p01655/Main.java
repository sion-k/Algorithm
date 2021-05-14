package baekjoon.p01655;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> low = 
		new PriorityQueue<>(N / 2, Collections.reverseOrder()); // �߰��� �Ʒ� ��
		PriorityQueue<Integer> high = new PriorityQueue<>(N / 2);; // �߰��� �� ��
		// N�� 2������ ��� ���� ó��
		if (N == 1) {
			bw.write(br.readLine()); bw.newLine();
			return;
		}
		if (N == 2) {
			int a = Integer.parseInt(br.readLine());
			int b = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(Math.min(a, b)));
			bw.newLine();
			return;
		}
		int a = Integer.parseInt(br.readLine());
		bw.write(String.valueOf(a)); bw.newLine();
		int b = Integer.parseInt(br.readLine());
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		bw.write(String.valueOf(a)); bw.newLine();
		low.offer(a); high.offer(b);
		for (int i = 1; i <= N - 2; i++) {
			int temp = Integer.parseInt(br.readLine());
			if (temp <= low.peek()) {low.offer(temp);}
			else {high.offer(temp);}
			PriorityQueue<Integer> fewer = low.size() < high.size() ? low : high;
			PriorityQueue<Integer> richer = low.size() > high.size() ? low : high;
			if (Math.abs(low.size() - high.size()) >= 2) {
				fewer.offer(richer.poll());
			}
			if (low.size() == high.size()) {// �߰� ���� 2��
				bw.write(String.valueOf(Math.min(low.peek(), high.peek())));
			} else { //�߰� ���� ū��
				bw.write(String.valueOf(richer.peek()));
			}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}