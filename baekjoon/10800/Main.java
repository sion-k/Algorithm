import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		// S[x] = x크기인 컬러볼들 (번호, 색)
		ArrayList<ArrayList<Pair>> S = new ArrayList<>(2001);
		for (int i = 0; i <= 2000; i++) S.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			S.get(s).add(new Pair(i, c));
		}
		int p = 0; // 지금까지 계산한 공들의 크기의 합
		int[] C = new int[N + 1]; // 색깔별 크기들의 합 저장
		int[] ret = new int[N + 1]; // i번째 공이 집을수 있는 공의 크기 합 저장
		for (int i = 1; i <= 2000; i++) {
			for (Pair x : S.get(i)) ret[x.num] = p - C[x.color];
			p += S.get(i).size() * i;
			for (Pair x : S.get(i)) C[x.color] += i;
		}
		for (int i = 1; i <= N; i++) bw.append(ret[i]).append("\n");
		System.out.print(bw);
	}

}
class Pair {
	int num, color;
	
	Pair (int n, int c) { num = n; color = c; }
}
