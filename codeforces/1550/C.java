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
			long sum = (long)N * (1 + N) / 2;
			Map<Integer, Integer> m = new HashMap<>();
			int head = 0; int tail = 0;
			while (head < N) {
				m.put(S[head], m.getOrDefault(S[head], 0) + 1);
				while (tail + 1 < N && (S[head] != S[tail] || m.get(S[tail + 1]) == null)) {
					m.put(S[tail + 1], m.getOrDefault(S[tail + 1], 0) + 1);
					tail++;
				}
				System.out.printf("%d, %d\n", head, tail);
				sum -= (N - tail - 1);
				m.put(S[head], m.get(S[head]) - 1);
				head++;
			}
			bw.append(sum);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}