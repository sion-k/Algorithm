package baekjoon.p01041;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] DICE = new int[6];
	
	static final int[][] point = { // 주사위의 8개의 꼭짓점
		{ 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 4 }, { 0, 3, 4 },
		{ 1, 2, 5 }, { 1, 3, 5 }, { 2, 4, 5 }, { 3, 4, 5 } 
	};
	
	static final int[][] edge = { // 주사위의 i번째 면과 인접하는 면들
		{ 1, 2, 3, 4 }, { 2, 3, 5 }, { 4, 5 }, { 4, 5 }, { 5 }
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		int maxFace = 1; int minFace = 50; int sumFace = 0;
		for (int i = 0; i < 6; i++) {
			DICE[i] = Integer.parseInt(st.nextToken());	
			sumFace += DICE[i];
			maxFace = Math.max(maxFace, DICE[i]);
			minFace = Math.min(minFace, DICE[i]);
		}
		int minPoint = 150;
		for (int i = 0; i < point.length; i++) {
			minPoint = Math.min(minPoint, 
			DICE[point[i][0]] + DICE[point[i][1]] + DICE[point[i][2]]);
		}
		
		int minEdge = 100;
		for (int i = 0; i < edge.length; i++) {
			for (int j = 0 ; j < edge[i].length; j++) {
				minEdge = Math.min(minEdge, DICE[i] + DICE[edge[i][j]]);
			}
		}
		long min = 0;
		if (N == 1) {
			min = sumFace - maxFace;
		} else {
			min += 4 * minPoint;
			min += 4 * minEdge * ((N - 1) + (N - 2));
			min += (4 * minFace *((long)(N - 1) * (N - 2)));
			min += (minFace * ((long)(N - 2) * (N - 2)));
		}
		System.out.println(min);
	}

}