import java.io.*;
import java.util.*;

public class Main {

	static String solve(int N, int K) {
		// 가장 많은 경우의 수가 나오는 A의 개수와 B의 개수
		int A = N / 2; int B = N - A;
		if (A * B < K) return "-1";
		// B...A...인 상태에서 K개까지 늘린다
		char[] ret = new char[N];
		Arrays.fill(ret, ' ');
		for (int i = 0; i < K / B; i++) ret[i] = 'A';
		// A * K / B + B... A...
		// ret[K / B]부터는 'B'가 B개 있다.
		for (int i = K / B; i < K / B + B; i++) ret[i] = 'B';
		// 단, 중간에 A를 하나 끼움
		if (K % B != 0) {
			ret[K / B + B - K % B] = 'A';
			ret[K / B + B] = 'B';
		}
		for (int i = 0; i < N; i++)
			if (ret[i] == ' ') ret[i] = 'A';
		return String.valueOf(ret);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		System.out.println(solve(N, K));
	}

}
