import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int P = Integer.parseInt(br.readLine());
			bw.append(2).append(" ").append(P - 1);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}