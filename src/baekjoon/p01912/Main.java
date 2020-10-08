package baekjoon.p01912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 빠른 입 출력, 문자열 처리
 */
public class Main {
	static int[] S;
	static ArrayList<Integer> cache;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		S = new int[N];
		cache = new ArrayList<>(N);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int sum = 0;
		// cache[i] = [0, i] 까지의 부분 합
		// 연속합[i, j] = cache[j] - cache[i - 1];
		for(int i = 0 ; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			sum += S[i];
			cache.add(sum);
		}
		br.close();
		System.out.println(cache);
		Collections.sort(cache);
		System.out.println(cache);
		System.out.println(cache.get(N - 1) - cache.get(0));
	}

}