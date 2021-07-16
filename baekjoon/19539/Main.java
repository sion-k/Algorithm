import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int sum = 0;
		int del = 0;
		int[] cnt = new int[2]; // 짝수 홀수 개수
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			sum += S[i];
			cnt[S[i] % 2]++;
			del += (S[i] % 2 == 0 ? S[i] / 2 - 1 : (S[i] - 1) / 2);
		}
		boolean flag = false;
		if (sum % 3 == 0) {
			if (cnt[1] == 0) flag = true;
			if (del >= (cnt[1] - cnt[0])) flag = true;
		}
		System.out.println(flag ? "YES" : "NO");
		
	}

}
