import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Pair> S = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			S.add(new Pair(d, v));
		}
		Collections.sort(S);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (Pair x : S) {
			if (pq.size() < x.deadline) {
				pq.offer(x.value);
			} else if (pq.peek() < x.value) {
				pq.poll(); pq.offer(x.value);
			}
		}
		int sum = 0;
		for (int x : pq) sum += x;
		System.out.println(sum);
	}

}

class Pair implements Comparable<Pair> {
	int deadline, value;
	
	Pair(int d, int v) { deadline = d; value = v; }
	
	@Override
	public int compareTo(Pair o) { return deadline - o.deadline; }
	
}
