package baekjoon.p13459;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	// 정점 start에서 bfs
//	static void bfs(int start) {
//		Queue<Integer> q = new LinkedList<>();
//		q.add(start);
//		BOOKED[start] = true;
//		
//		while(!q.isEmpty()) {
//			int here = q.poll();
//			for (int next = 1; next <= N; next++) {
//				if(EDGE[here][next] && !BOOKED[next]) {
//					q.offer(next);
//					BOOKED[next] = true;
//				}
//			}
//		}
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Integer.parseInt(st.nextToken());
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.close();
	}

}
