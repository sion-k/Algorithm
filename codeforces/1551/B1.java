import java.io.*;
import java.util.*;

public class B1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			String S = br.readLine();
			int[] C = new int[26];
			for (char x : S.toCharArray()) C[x - 'a']++;
			int cnt1 = 0; int cnt2 = 0;
			for (int i = 0; i < 26; i++)
				if (C[i] >= 2) cnt2++;
				else if (C[i] == 1) cnt1++;
			bw.append(cnt2 + cnt1 / 2);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}