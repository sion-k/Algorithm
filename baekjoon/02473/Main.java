import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] S = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(S);
		int p = 0; int q = 1; int r = 2;
		long min = Math.abs(S[p] + S[q] + S[r]);
		for (int i = 0; i < N - 2; i++) {
			long x = -S[i];
			int head = i + 1; int tail = N - 1;
			// x에 가장 가까운 S[head] + S[tail]을 찾는다
			while (head < tail) {
				long abs = Math.abs(S[i] + S[head] + S[tail]);
				if (min > abs) {
					min = abs;
					p = i; q = head; r = tail;
				}
				if (S[head] + S[tail] < x) head++;
				else tail--;
			}
		}
		System.out.printf("%d %d %d\n", S[p], S[q], S[r]);
	}

}
