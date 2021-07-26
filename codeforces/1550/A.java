import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int S = Integer.parseInt(br.readLine());
			int i = 1; int sum = 0; int cnt = 0;
			while (sum + i < S) {
				sum += i;
				i += 2;
				cnt++;
			}
			bw.append(cnt + 1);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}