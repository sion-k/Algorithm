package baekjoon.p20529;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static final char[] MBTI = { 'E', 'S', 'T', 'J' };
	static int[] count;
	static final int INF = 12;

	// 비트마스킹을 사용해서 16가지의 MBTI유형을 4자리 이진수로 표현
	static int getIndex(String mbti) {
		int bin = 0;
		for (int i = 0; i < 4; i++)
			if (mbti.charAt(i) == MBTI[i])
				bin += ((int) Math.pow(2, i));
		return bin;
	}

	// 두 유형 사이의 심리적 거리
	static int dist(int i, int j) {
		String s1 = String.format("%04d", Integer.parseInt(Integer.toBinaryString(i).toString()));
		String s2 = String.format("%04d", Integer.parseInt(Integer.toBinaryString(j).toString()));
		int dist = 0;
		for (int iter = 0; iter < 4; iter++)
			if (s1.charAt(iter) != s2.charAt(iter))
				dist++;
		return dist;
	}

	// count배열에서 중복을 허용해서 3개를 고르는 경우를 모두 시도한 뒤 최소 심리 거리 반환
	static int dfs(int here, ArrayList<Integer> picked) {
		if (here >= 16) {
			if (picked.size() == 3)
				return dist(picked.get(0), picked.get(1)) + dist(picked.get(1), picked.get(2))
				+ dist(picked.get(2), picked.get(0));
			else
				return INF;

		}
		int min = INF;
		// 고르지 않는 경우
		min = Math.min(min, dfs(here + 1, picked));
		// 고르는 경우
		if (picked.size() < 3 && count[here] > 0) {
			count[here]--;
			picked.add(here);
			min = Math.min(min, dfs(here, picked));
			picked.remove(picked.size() - 1);
			count[here]++;
		}
		return min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			count = new int[16];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				count[getIndex(st.nextToken())]++;
			bw.write(String.valueOf(dfs(0, new ArrayList<Integer>(3))));
			bw.newLine();
		}
		bw.close();
	}

}