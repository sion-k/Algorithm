import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			char[] S = br.readLine().toCharArray();
			int index = -1;
			for (int i = 0; i < S.length; i++) {
				if ((S[i] - '0') % 2 == 0) {
					index = i; break;
				}
			}
			if (index == -1) {
				bw.append(-1);
			} else if ((S[S.length - 1] - '0') % 2 == 0) {
				bw.append(0);
			} else if (index == 0) {
				bw.append(1);
			} else {
				bw.append(2);
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}