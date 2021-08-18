import java.io.*;
import java.util.*;

public class Main {
	static String[] S;
	
	// i번째 알파벳 부터 K개의 알파벳을 배우는 경우를 모두 시도
	static int btk(int i, int K, boolean[] a) {
		if (i == 26) return K == 0 ? cnt(a) : 0;
		int max = 0;
		if (K > 0 && !a[i]) {
			a[i] = true;
			max = Math.max(max, btk(i + 1, K - 1, a));
			a[i] = false;
		}
		max = Math.max(max, btk(i + 1, K, a));
		return max;
	}
	
	static int cnt(boolean[] a) {
		int ret = 0;
		for (String x : S) if (f(x, a)) ret++;
		return ret;
	}
	
	static boolean f(String str, boolean[] a) {
		for (int i = 0; i < str.length(); i++)
			if (!a[str.charAt(i) - 'a'])
				return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		S = new String[N];
		for (int i = 0; i < N; i++) {
			S[i] = br.readLine();
			S[i] = S[i].substring(4, S[i].length() - 4);
		}
		int max = 0;
		if (K >= 5) {
			boolean[] a = new boolean[26];
			a['a' - 'a'] = true;
			a['c' - 'a'] = true;
			a['i' - 'a'] = true;
			a['n' - 'a'] = true;
			a['t' - 'a'] = true;
			max = Math.max(max, btk(0, K - 5, a));
		}
		System.out.println(max);
	}

}
