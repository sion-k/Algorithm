import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			char[] S = br.readLine().toCharArray();
			char[] R = br.readLine().toCharArray();
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (R[i] == '0') continue;
				if (S[i] == '0') {
					S[i] = '2';
					cnt++;
				} else if (i - 1 >= 0 && S[i - 1] == '1') {
					S[i - 1] = '2';
					cnt++;
				} else if (i + 1 < N && S[i + 1] == '1') {
					S[i + 1] = '2';
					cnt++;
				}
			}
			bw.append(cnt);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}