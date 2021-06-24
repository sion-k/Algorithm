import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final String NEW_LINE = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Queue<Pair> q = new LinkedList<Pair>();
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				q.offer(new Pair(i, Integer.parseInt(st.nextToken())));
			int n = 1;
			while (!q.isEmpty()) {
				Pair p = q.peek();
				// Queue 내에 p보다 큰 원소가 없을 경우 뽑을 수 있다
				if (p.priority == Collections.max(q).priority) {
					// Query한 원소인 경우 답변
					if (q.peek().index == M) {
						ans.append(n).append(NEW_LINE);
						break;
					}
					q.poll();
					n++;
				// 더 큰 원소가 있다면 Queue의 맨 뒤에 배치한다
				} else {
					q.offer(q.poll());
				}
			}
		}
		System.out.print(ans);
	}

}

class Pair implements Comparable<Pair>{
	int index; int priority;
	public Pair(int i, int p) {index = i; priority = p;}
	@Override
	public int compareTo(Pair o) {return priority - o.priority;}
}
