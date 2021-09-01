import java.io.*;
import java.util.*;

public class Main {
	// 정렬된 배열 S[head, tail]에 합이 k인 두 원소가 존재하는지 반환
	static boolean f(int[] S, int head, int tail, int k) {
		while (head < tail) {
			int sum = S[head] + S[tail];
			if (sum < k) head++;
			else if (sum > k) tail--;
			else return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] S = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(S);
		// R[x] = S[i] + S[j] = x인 경우가 존재하는지
		// 존재한다면 (i < j) 인 j인덱스를 저장, 중복된다면 가장 작은 값. 없다면 INF
		int[] R = new int[400001];
		Arrays.fill(R, N);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int sum = S[i] + S[j];
				R[sum] = Math.min(R[sum], j);
			}
		}
		boolean flag = false;
		for (int x = 1; x <= 400000; x++) {
			if (f(S, R[x] + 1, N - 1, W - x)) {
				flag = true;
				break;
			}
		}
		System.out.println(flag ? "YES" : "NO");
	}

}
