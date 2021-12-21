import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			String S = br.readLine();
			boolean flag = S.substring(0, S.length() / 2).equals(S.substring(S.length() / 2));
			bw.append(flag ? "YES" : "NO").append("\n");
		}
		System.out.print(bw);
	}

}