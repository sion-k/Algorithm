import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			char[] S = br.readLine().toCharArray();
			char[] R = new char[N];
			for (int i = 0; i < N; i++) {
				if (S[i] == 'U') R[i] = 'D';
				else if (S[i] == 'D') R[i] = 'U';
				else R[i] = S[i];
			}
			bw.append(String.valueOf(R));
			bw.append("\n");
		}
		System.out.print(bw);
	}

}