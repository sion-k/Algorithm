package codeforce.r1539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long n = Long.parseLong(st.nextToken());
			long x = Long.parseLong(st.nextToken());
			long t = Long.parseLong(st.nextToken());
			long sum = 0;
			for (int i = 0; i < n; i++) {
				sum += Math.min((x * i + t), (n - 1) * x) / x;
			}
			// x * hi + t >= (n - 1) * x가 되기 시작하는 hi를 찾는다
			// x * lo + t < (n - 1) * x
			long lo = 0; long hi = n;
			while (lo + 1 < hi) {
				long mid = (lo + hi) / 2;
				if (x * mid + t >= (n - 1) * x) hi = mid;
				else lo = mid;
			}
			sum += (n - 1) * n;
			sum -= (n * (n - 1) / 2);
			sum -= (n * (n - 1) / 2);
			ans.append(sum).append("\n");
		}
		System.out.print(ans);
	}
	
}
