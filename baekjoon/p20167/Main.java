package baekjoon.p20167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] S;
	static int[] cache;
	
	// i��°���� ����ġ�ų� �Ա� ������ �� ���� ��
	// ���� �� �ִ� �ִ� Ż�� ������
	static int dp(int i) {
		if (i == N) return 0;
		if (cache[i] != -1) return cache[i];
		int max = dp(i + 1); // ����ġ�� ���
		// �Ա� �����ϴ� ���
		int here = i; int sum = S[here];
		while (++here < N && sum < K) sum += S[here];
		max = Math.max(max, sum - K + dp(here));
		return cache[i] = max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		S = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		cache = new int[N];
		Arrays.fill(cache, -1);
		System.out.println(dp(0));
	}
	
}