package baekjoon.p1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 빠른 입 출력, 문자열 처리
 */
public class Main {
	static int[] S;
	static int[] cache;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		S = new int[N + 1];
		cache = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 1; i <= N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		
		int sum = 0;
		// cache[i] = [0, i] 까지의 부분 합
		// [i, j] = cache[j] - cache[i - 1];
		for(int i = 1 ; i <= N; i++) {
			sum += S[i];
			cache[i] = sum;
		}
		
		int max = -1000 * 100000;
		// 모든 [i, j] 구간 순회
		for(int i = 1; i <= N; i++) {
			for(int j = i; j <= N; j++) {
				max = Math.max(max, cache[j] - cache[i - 1]);
			}
		}
		
		System.out.println(max);
	}

}

