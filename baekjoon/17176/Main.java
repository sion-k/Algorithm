import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cipher = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++)
			cipher[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(cipher);

		char[] plain = br.readLine().toCharArray();
		Arrays.sort(plain);

		for (int i = 0; i < N; i++) {
			if (plain[i] == ' ') {
				plain[i] = 0;
			} else if ('A' <= plain[i] && plain[i] <= 'Z') {
				plain[i] -= ('A' - 1);
			} else {
				plain[i] -= ('a' - 1 - 26);
			}
			if (plain[i] != cipher[i]) {
				System.out.println("n");
				return;
			}
		}
		System.out.println("y");
		br.close();
	}

}
