import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] a = new int[101];
			for (int i = 0; i < N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
				a[S[i]]++;
			}
			int found = 0;
			for (int i = 1; i <= 100; i++)
				if (a[i] == 1) {found = i; break;}
			for (int i = 0; i < N; i++)
				if (S[i] == found) { bw.append(i + 1); break; }
			bw.append("\n");
		}
		System.out.print(bw);
	}

}
