import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t1 = Math.min(a, b);
			int t2 = Math.max(a, b) - t1;
			int t3 = Math.min(t2 / 2, t1);
			t1 -= t3;
			int ret = t3 + t1 / 2;
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}