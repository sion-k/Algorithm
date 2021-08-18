import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a > b) { long temp = a; a = b; b = temp; }
			long n = 2 * (b - a);
			boolean flag = true;
			if (c > n) flag = false;
			if (n < a || n < b) flag = false;
			long d = c + n / 2 > n ? c - n / 2 : c + n / 2;
			bw.append(flag ? d : -1);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}