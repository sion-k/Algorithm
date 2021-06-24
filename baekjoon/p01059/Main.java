package baekjoon.p01059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		boolean[] luckySet = new boolean[1001];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < L; i++) {
			luckySet[Integer.parseInt(st.nextToken())] = true;
		}
		int mid = Integer.parseInt(br.readLine());
		if (luckySet[mid]) {System.out.println(0); return;}
		int left = mid; // 가장 왼쪽 LuckySet
		while (left > 0 && !luckySet[left]) {left--;}
		int right = mid;// 가장 오른쪽 LuckySet
		while (right <= 1000 && !luckySet[right]) {right++;}
		int cnt = 0;
		for (int start = left + 1; start <= mid; start++) {
			for (int end = mid; end <= right - 1; end++) {
				cnt++;
			}
		}
		System.out.println(cnt - 1);
	}

}