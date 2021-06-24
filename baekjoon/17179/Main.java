import java.util.*;
import java.io.*;

public class Main {
	static int[] S; static int N;
	static int L;
	
	// 주어진 수열에서 최소 거리가 d이상이 되게 q개를 선택할 수 있는지 반환
	static boolean f(int d, int q) {
		int last = 0; int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (S[i] - last >= d) {last = S[i]; cnt++;}
			if (cnt == q) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		S = new int[N];
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			int q = Integer.parseInt(br.readLine());
			int lo = 1; int hi = L;
			// f(lo) == true && f(hi) == false인 lo반환
			while (lo + 1 < hi) {
				int mid = (lo + hi) / 2;
				if (f(mid, q)) lo = mid;
				else hi = mid;
			}
			ans.append(lo).append("\n");
		}
		System.out.print(ans);
	}

}
