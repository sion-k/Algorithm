import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		String S = br.readLine();
		int u = 0; int v = 0;
		boolean found = false;
		for (int A = 1; A <= 999; A++) {
			for (int B = A; B <= 999; B++) {
				if (S.startsWith(String.valueOf(A)) && S.endsWith(String.valueOf(B))) {
					StringBuilder sb = new StringBuilder();
					for (int i = A; i <= B; i++) sb.append(i);
					if (sb.toString().equals(S)) {
						found = true;
						u = A; v = B;
					}
				}
				if (found) break;
			}
			if (found) break;
		}
		bw.append(u).append(" ").append(v).append("\n");
		System.out.print(bw);
	}

}
