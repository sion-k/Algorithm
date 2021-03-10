package baekjoon.p01493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Pair[] S;
	static long volume;
	
	// length × width × height 크기의 박스에 i번째 큐브를 채워 넣을 때
	// 박스를 채우는데 필요한 최소 개수
	static int solve(int l, int w, int h, int i) {
		if (l == 0 || w == 0 || h == 0) return 0;
		int min = Math.min(l, Math.min(w, h));
		while (i < N && ((int)(Math.pow(2, S[i].A)) > min || S[i].B == 0)) i++;
		if (i == N) return 0;
		int r = (int)Math.pow(2, S[i].A); // 현재 박스에 놓을 수 있는 최대 크기 큐브
		S[i].B--; volume -= Math.pow((long)r, 3); // 큐브를 왼쪽 아래 구석에 놓고 나머지 3부분으로 나눠서 분할정복
		return 1 + solve(r, r, h - r, i) + solve(l, w - r, h, i) + solve(l - r, r, h, i); 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int l = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		S = new Pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			S[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		volume = (long)l * w * h;
		Arrays.sort(S, (u, v) -> v.A - u.A);
		int ret = solve(l, w, h, 0);
		System.out.println(volume == 0 ? ret : -1);
	}
	
}
class Pair {
	int A, B;
	public Pair(int a, int b) {A = a; B = b;}
	public String toString() {return A + " " + B;}
}