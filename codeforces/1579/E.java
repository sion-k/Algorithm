import java.io.*;
import java.util.*;

public class E {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			Deque<Integer> dq = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				if (dq.isEmpty() || x < dq.peekFirst()) {
					dq.offerFirst(x);
				} else {
					dq.offerLast(x);
				}
			}
			for (int x : dq) {
				bw.append(x).append(" ");
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}