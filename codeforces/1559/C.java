import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			boolean flag = false; int k = 0;
			Deque<Integer> dq = new LinkedList<>();
			if (S[0] == 1) {
				flag = true;
				for (int i = 1; i <= N; i++) dq.offer(i);
				dq.offerFirst(N + 1);
			} else if (S[N - 1] == 0) {
				flag = true;
				for (int i = 1; i <= N; i++) dq.offer(i);
				dq.offerLast(N + 1);
			} else {
				for (int i = 0; i < N - 1; i++)
					if (S[i] == 0 && S[i + 1] == 1) {
						flag = true;
						k = i;
					}
				if (flag) {
					for (int i = 0; i < N; i++) {
						dq.offer(i + 1);
						if (i == k) dq.offer(N + 1);
					}
				}
			}
			if (flag) {
				for (int x : dq)
					bw.append(x).append(" ");
			} else {
				bw.append(-1);
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}