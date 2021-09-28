import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			char[] S = br.readLine().toCharArray();
			int[] cnt = new int[3];
			for (char x : S) {
				cnt[x - 'A']++;
			}
			boolean flag = (cnt[0] + cnt[2] == cnt[1]);
			bw.append(flag ? "YES" : "NO");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}