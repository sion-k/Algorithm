import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) pq.offer(Long.parseLong(st.nextToken()));
		for (int i = 0; i < M; i++) {
			long t = pq.poll() + pq.poll();
			pq.offer(t); pq.offer(t);
		}
		long sum = 0;
		for (long x : pq) sum += x;
		System.out.println(sum);
	}

}
