import java.io.*;
import java.util.*;

public class D {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				int x = Integer.parseInt(st.nextToken());
				if (x > 0) pq.offer(new Pair(i, x));
			}
			int t = 0;
			StringBuilder temp = new StringBuilder();
			while (pq.size() >= 2) {
				Pair p = pq.poll();
				Pair q = pq.poll();
				temp.append(String.format("%d %d\n", p.index, q.index));
				if (--p.value > 0) pq.offer(p);
				if (--q.value > 0) pq.offer(q);
				t++;
			}
			bw.append(t).append("\n");
			bw.append(temp);
		}
		System.out.print(bw);
	}

}
class Pair implements Comparable<Pair> {
	int index, value;

	Pair(int i, int v) { index = i; value = v; }

	@Override
	public int compareTo(Pair o) { return o.value - value; }
}
