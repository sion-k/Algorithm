package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] S = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		// 존재 가능한 카드 3장의 합의 범위는 [3, 300], 해당 합이 존재하는지 여부 저장
		boolean[] set = new boolean[301];
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++)
					set[S[i] + S[j] + S[k]] = true;
		// K번째 답이 존재하지 않으면 -1을 반환
		int ret = -1;
		// [300, 3]구간을 순회하면서 K번째 답을 찾는다
		for (int i = 300; i >= 3; i--) {
			// 해당 값이 존재하는 경우
			if (set[i]) K--;
			// K가 0이라면 현재 i의 값이 K번째의 값임
			if (K == 0) {ret = i; break;}
		}
		System.out.println(ret);
	}

}