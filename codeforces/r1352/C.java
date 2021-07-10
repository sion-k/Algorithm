package codeforce.r1352;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class C {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			long lo = 0; long hi = 987654321987654L;
			while (lo + 1 < hi) {
				long mid = (lo + hi) / 2;
				if ((mid - mid / N) >= K) hi = mid;
				else lo = mid;
			}
			ans.append(hi).append("\n");
		}
		System.out.print(ans);
	}
}