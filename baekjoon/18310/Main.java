import java.io.*;
import java.util.*;

public class Main {
	
	static long sum(long[] S, int i, int j) {
		if (i > j) return 0;
		return S[j] - (i == 0 ? 0 : S[i - 1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		// P[x] = x위치 이하 좌표값의 합 C[x] = x위치 이하 좌표의 개수 합      
		long[] P = new long[100001]; long[] C = new long[100001];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		for (int x : S) { P[x] += x; C[x]++; }
		for (int i = 1; i <= 100000; i++) { 
			P[i] += P[i - 1]; C[i] += C[i - 1];
		}
		long min = Long.MAX_VALUE;
		int ret = 100000;
		for (int x : S) {
			long sum = sum(C, 0, x - 1) * x - sum(P, 0, x - 1) +
			sum(P, x + 1, 100000) - sum(C, x + 1, 100000) * x;
			if (min >= sum) {
				if (min > sum || (min == sum && ret > x)) ret = x;
				min = sum;
			}
		}
		System.out.println(ret);
	}

}
