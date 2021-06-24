import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] S;

	private static int lis() {
		ArrayList<Integer> lis = new ArrayList<>();
		lis.add(S[0]);
		for (int i = 1; i < N; i++) {
			if (S[i] > lis.get(lis.size() - 1)) {
				lis.add(S[i]);
			} else {
				int bs = Collections.binarySearch(lis, S[i]);
				if (bs < 0) {
					bs = bs * -1 - 1;
				}
				lis.set(bs, S[i]);
			}
		}
		return lis.size();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		System.out.println(lis());
	}

}
