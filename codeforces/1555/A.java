import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			long N = Long.parseLong(br.readLine());
			long ret = N < 6 ? 15 : N / 2 * 5;
			if (N >= 6 && N % 2 == 1) ret += 5;
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}