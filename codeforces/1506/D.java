package codeforce.r1506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			HashMap<Integer, Integer> map = new HashMap<>();
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				map.put(x, map.getOrDefault(x, 0) + 1);				
			}
			List<Integer> temp = new ArrayList<>(map.values());
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			for (int x : temp) pq.offer(x);
			while (pq.size() >= 2) {
				int a = pq.poll(); int b = pq.poll();
				if (a - 1 > 0) pq.offer(a - 1);
				if (b - 1 > 0) pq.offer(b - 1);
			}
			System.out.println(pq.isEmpty() ? 0 : pq.poll());
		}
	}
	
}