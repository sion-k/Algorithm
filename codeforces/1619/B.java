import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		Set<Long> S = new HashSet<>();
		for (long i = 1; i < 100000; i++) {
			if (i * i <= 1000000000) {
				S.add(i * i);
			}
			if (i * i * i <= 1000000000) {
				S.add(i * i * i);
			}
		}
		ArrayList<Long> R = new ArrayList<>(S);
		Collections.sort(R);
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int ret = 0;
			for (int i = 0; i < R.size(); i++) {
				if (N >= R.get(i)) {
					ret++;
				}
			}
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}