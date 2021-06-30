import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(S);
		int head = 0; int tail = N - 1;
		int sum = Math.abs(S[head] + S[tail]);
		int min = sum; int[] p = new int[] { head, tail };
		while (head < tail) {
			// S[head]와 더했을 때 가장 0에 가까운 S[tail]까지 이동시킨다
			while (head < tail - 1 &&
			Math.abs(S[head] + S[tail - 1]) < sum) {
				tail--;
				sum = Math.abs(S[head] + S[tail]);
			}
			if (min > sum) {
				min = sum;
				p[0] = head; p[1] = tail;
			}
			head++;
			sum = Math.abs(S[head] + S[tail]);
		}
		System.out.printf("%d %d\n", S[p[0]], S[p[1]]);
	}

}
