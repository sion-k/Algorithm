import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			long k = Long.parseLong(br.readLine());
			long n = 1;
			while (n * n < k) n++;
			long t = n * n - n + 1;
			long d = Math.abs(t - k);
			if (k >= t) bw.append(String.format("%d %d", n, n - d));
			else bw.append(String.format("%d %d", n - d, n));
			bw.append("\n");
		}
		System.out.print(bw);
	}

}