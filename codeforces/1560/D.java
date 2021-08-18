import java.io.*;
import java.util.*;

public class D {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			String A = br.readLine();
			int min = Integer.MAX_VALUE;
			long d = 1;
			for (int i = 0; i <= 62; i++) {
				String B = String.valueOf(d << i);
				int S = A.length();
				int L = 0;
				int t2 = 0;
				for (int t1 = 0; t1 < A.length(); t1++) {
					if (t2 < B.length() && A.charAt(t1) == B.charAt(t2)) {
						L++; t2++;
					}
				}
				int D = B.length();
				min = Math.min(min, S - L + D - L);
			}
			bw.append(min);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}