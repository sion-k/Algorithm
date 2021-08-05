import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Long> S = new ArrayList<>(N);
		for (int i = 0; i < N; i++) S.add(Long.parseLong(br.readLine()));
		Collections.sort(S);
		int head = 0; int tail = 0;
		int max = 0; long ret = 0;
		while (head < N) {
			while (tail + 1 < N && Long.compare(S.get(tail + 1), S.get(head)) == 0) tail++;
			if (max < tail - head + 1) {
				max = tail - head + 1;
				ret = S.get(head);
			}
			head++;
		}
		System.out.println(ret);
	}

}
