package codeforce.r693;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> even = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> odd = new PriorityQueue<>(Collections.reverseOrder());
			long evenSum = 0;
			long oddSum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int t = Integer.parseInt(st.nextToken());
				if (t % 2 == 0) {
					even.offer(t);
					evenSum += t;
				} else {
					odd.offer(t);
					oddSum += t;
				}
			}
			long A = 0; long B = 0;
			while (!even.isEmpty() || !odd.isEmpty()) {
				// Alice
				if (A + evenSum <= B + oddSum && !odd.isEmpty()) {
					oddSum -= odd.poll();
				} else if (!even.isEmpty()){
					int t = even.poll();
					A += t;
					evenSum -= t;
				}
				// Bob
				if (A + evenSum >= B + oddSum && !even.isEmpty()) {
					evenSum -= even.poll();
				} else if (!odd.isEmpty()){
					int t = odd.poll();
					B += t;
					oddSum -= t;
				}
			}
			String judge = "";
			if (A > B) {
				judge = "Alice";
			} else if (A < B) {
				judge = "Bob";
			} else {
				judge = "Tie";
			}
			bw.write(judge);
			bw.newLine();
		}
		bw.close();
	}

}