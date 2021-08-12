import java.io.*;
import java.util.*;

public class Main {

	static long f(int N, int[] S, int picked) {
		long cnt = 0;
		int head = 0; int tail = N - 1;
		while (head < picked && picked < tail) {
			int sum = S[head] + S[picked] + S[tail];
			if (sum < 0) {
				head++;
			} else if (sum > 0) {
				tail--;
			} else {
				int l = 1; int r = 1;
				while (head + 1 < picked && S[head + 1] + S[picked] + S[tail] == 0) {
					head++;
					l++;
				}
				while (tail - 1 > picked && S[head] + S[picked] + S[tail - 1] == 0) {
					tail--;
					r++;
				}
				cnt += l * r;
				head++; tail--;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().strip());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(S);
		long sum = 0;
		for (int i = 1; i < N - 1; i++) sum += f(N, S, i);
		System.out.println(sum);
	}

}
