import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		ArrayList<Integer> S = new ArrayList<>();
		int i = 1;
		while (S.size() <= 1000) {
			if (!(i % 3 == 0 || i % 10 == 3))
				S.add(i);
			i++;
		}
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			bw.append(S.get(N - 1));
			bw.append("\n");
		}
		System.out.print(bw);
	}

}