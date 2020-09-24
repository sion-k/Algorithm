package baekjoon.p13305;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[] R;
	static int[] C;
	static int   N;
	
	//here에서 끝 도시까지 가는데 최소 비용을 반환한다
	static int costMin(int here) {
		if (here == N - 1) {return 0;}
		int next = here + 1; int movedDis = R[here];
		// 24289 178 26432 423
		while(next < N && C[next] >= C[here]) {
			movedDis += R[next]; next++;
		}
		System.out.println(here);
		return movedDis * C[here] + costMin(next);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		R = new int[N]; // 각 도시마다 오른쪽에 도로가 대응된다. (맨 오른쪽 도시 제외)
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N - 1; i++) {
			R[i] = Integer.parseInt(st.nextToken());
		}
		
		C = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 0; i < N; i++) {
			C[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(costMin(0));
	}

}
