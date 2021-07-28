import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int t = N / 3; int r = N % 3;
			int p = t; int q = t;
			if (r == 2) {
				q++;
			} else if(r == 1) {
				p++;
			}
			bw.append(p).append(" ").append(q);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}