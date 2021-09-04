import java.io.*;
import java.util.*;

public class Main {

	static String solve(int N, int K) {
		int A = N / 3; int B = N / 3; int C = N / 3;
		if (N % 3 >= 1) C++;
		if (N % 3 >= 2) B++;
		if (K > A * B + B * C + C * A) return "-1";
		ArrayList<Character> S = new ArrayList<>();
		int range = Math.min(A, K / (B + C));
		for (int i = 0; i < range; i++) { 
			S.add('A'); A--;
			K -= (B + C);
		}
		if (A > 0) {
			while (B + C - K > 0) {
				if (C > 0) {
					S.add('C');
					C--;
				} else {
					S.add('B');
					B--;
				}
			}
			S.add('A'); A--;
			while (B + C > 0) {
				if (C > 0) {
					S.add('C');
					C--;
				} else {
					S.add('B');
					B--;
				}
			}
			while (A > 0) {
				S.add('A');
				A--;
			}
		} else {
			for (int i = 0; i < K / C; i++) { S.add('B'); B--;}
			K = K % C;
			if (B > 0) {
				while (C - K > 0) {
					S.add('C'); C--;
				}
				S.add('B'); B--;
				while (C > 0) {
					S.add('C'); C--;
				}
				while (B > 0) {
					S.add('B'); B--;
				}
			} else {
				while (C > 0) {
					S.add('C'); C--;
				}
			}
		}
		char[] ret = new char[S.size()];
		for (int i = 0; i < S.size(); i++) ret[i] = S.get(i);
		return String.valueOf(ret);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		System.out.println(solve(N, K));			
	}

}
