import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			bw.append(N).append(" ");
			for (int i = 1; i <= N - 1; i++) bw.append(i).append(" ");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}