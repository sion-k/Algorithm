import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static long move(int here, int moveWay) {
		if (moveWay == 0) {return 2 * here;}
		else {return 10 * (long)here + 1;}
	}

	static int BFS(int A, int B) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(A);
		Map<Integer, Integer> dist = new HashMap<>();
		dist.put(A, 1);
		while (!q.isEmpty()) {
			int here = q.poll();
			for (int d = 0; d < 2; d++) {
				long there = move(here, d);
				if (there <= B && !dist.containsKey((int)there)) {
					dist.put((int)there, dist.get(here) + 1);
					q.offer((int)there);
					if (there == B) {return dist.get((int)there);}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		System.out.println(BFS(A, B));
	}

}
