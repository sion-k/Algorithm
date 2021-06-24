package baekjoon.p13305;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[] R;
	static int[] C;
	static int   N;
	
	static long dis(int start, int end) {
		long ret = 0;
		for(int i = start; i < end; i++) {ret += R[i];}
		return ret;
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
		
		int min = 0; long cost = 0;
		for (int i = 1; i < N; i++) {
			if(C[i] < C[min] || i == N - 1) {
				cost += (dis(min, i) * C[min]);
				min = i;
			}
		}
		System.out.println(cost);
	}

}
