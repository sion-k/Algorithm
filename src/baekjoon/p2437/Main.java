package baekjoon.p2437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	// 추들의 무게
	static Integer[] U;
	
	
	// 추 U로 주어진 무게 W를 똑같이 만들어 낼 수 있는지 반환
	static boolean sameWeight(int W) {
		if (W == 0) {return true;}
		int sum = 0;
		for (int i = 0; i < U.length; i++) {
			int added = sum + U[i];
			if (added <= W) {sum = added;}
			if (sum == W) {return true;}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		U = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 0; i < N; i++) {
			U[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(U, Collections.reverseOrder());
		
		int maxEsti = 0;
		for (int u : U) {maxEsti += u;}

		int w;
		for (w = 1; w + 2 <= maxEsti; w += 2) {
			if (!sameWeight(w)) {
				if (sameWeight(w - 1) && sameWeight(w + 1)) {

				} else {
					System.out.println(w);
					return;
				}
			}
		}
		System.out.println(w);
	}

}
