import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Pair> S = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			S.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(S, (u, v) -> u.S - v.S);
		PriorityQueue<Pair> pq = new PriorityQueue<>((u, v) -> u.T - v.T);
		int max = 1;
		for (Pair x : S) {
			while (!pq.isEmpty() && pq.peek().T <= x.S) pq.poll();
			pq.offer(x);
			max = Math.max(max, pq.size());
		}
		System.out.println(max);
	}
	
}
class Pair {
	int S, T;
	public Pair(int s, int t) {S = s; T = t;}
}
