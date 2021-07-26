import java.io.*;
import java.util.*;
import java.util.regex.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			char[] S = br.readLine().toCharArray();
			char[] T = br.readLine().toCharArray();
			boolean flag = false;
			for (int i = 0; i < S.length; i++) {
				for (int j = 0; j < T.length; j++) {
					if (T[j] != S[i]) continue;
					int s = 0;
					while (i - s >= 0 && j - s >= 0 && S[i - s] == T[j - s]) s++;
					int e = 1;
					while (i - e >= 0&& j + e < T.length && S[i - e] == T[j + e]) e++;
					if (s + e - 1 == T.length) flag = true;
				}
			}
			bw.append(flag ? "YES" : "NO");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}