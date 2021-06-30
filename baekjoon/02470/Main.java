import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> S = new ArrayList<>(N);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S.add(Integer.parseInt(st.nextToken()));
		Collections.sort(S);
		int head = 0; int tail = N - 1;
		int sum = S.get(head) + S.get(tail);
		int min = Math.abs(sum); int[] p = new int[] { head, tail };
		while (head < tail) {
			// S[head]와 더했을 때 가장 0에 가까운 S[tail]까지 이동시킨다
			while (head < tail - 1 &&
			Math.abs(S.get(head) + S.get(tail - 1)) < Math.abs(sum)) {
				tail--;
				sum = S.get(head) + S.get(tail);
			}
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				p[0] = head; p[1] = tail;
			}
			head++;
			sum = S.get(head) + S.get(tail);
		}
		System.out.printf("%d %d\n", S.get(p[0]), S.get(p[1]));
	}

}
