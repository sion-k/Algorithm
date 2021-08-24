import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		long cnt = 0;
		int head = 0; int tail = 0;
		int sum = S[head];
		while (head < N) {
			while (tail + 1 < N && sum + S[tail + 1] <= K) {
				sum += S[tail + 1];
				tail++;
			}
			cnt += N - tail - 1;
			sum -= S[head];
			head++;
		}
		System.out.println(cnt);
	}

}
