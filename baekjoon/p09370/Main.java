package baekjoon.p09370;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Pair implements Comparable<Pair> {
		int num; int cost;
		public Pair(int n, int c) {num = n; cost = c;}
		@Override
		public int compareTo(Pair o) {
			return cost - o.cost;
		}
	}

	static int N; // 정점의 개수, 정점의 번호는 [1, N]이다
	static ArrayList<ArrayList<Pair>> adj; // 인접 리스트 방식 표현
	static final int INF = 500000001;

	// 시작점 src로 부터 최단 경로 길이를 담은 배열을 반환한다
	static int[] dijkstra(int src) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);// 어떤 경로도 이보다 클 수 없음
		dist[src] = 0;// src 자신으로의 최단 경로 길이는 0
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		while(!pq.isEmpty()) {
			Pair p = pq.poll(); int here = p.num; int cost = p.cost;
			if(dist[here] < cost) {continue;}
			for (int i = 0; i < adj.get(here).size(); i++) {
				int there = adj.get(here).get(i).num;
				int nextDist = cost + adj.get(here).get(i).cost;
				// 기존에 발견한 것보다 더 짧은 경로를 발견 한 경우
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int C = Integer.parseInt(br.readLine());
		for (int c = 0; c < C; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>(N + 1);
			adj.add(null); // 0번째 정점은 없음
			for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}

			int M = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adj.get(from).add(new Pair(to, weight));// 양방향 간선
				adj.get(to).add(new Pair(from, weight));// 가중치는 [1, 1000]
			}

			ArrayList<Integer> answer = new ArrayList<>();
			int[] startTo = dijkstra(s); // 시작점에서 다익스트라
			int gh = dijkstra(g)[h]; // g-h사이 최단 거리
			for (int i = 0; i < T; i++) {
				int cand = Integer.parseInt(br.readLine());
				// 애초에 후보점에 도달할 수 없는 경우 제외
				if (startTo[cand] >= INF) {continue;}
				int[] candTo = dijkstra(cand); // 후보점에서 다익스트라
				// g-h로 이동할 때 후보점까지 최단 경로 길이
				int ghCand = startTo[g] + gh + candTo[h];
				// h-g로 이동할 때 후보점까지 최단 경로 길이
				int hgCand = startTo[h] + gh + candTo[g];
				// 둘중 적어도 하나는 후보점까지의 최단 경로와 같아야 후보가 될 수 있다
				if (startTo[cand] == ghCand || startTo[cand] == hgCand) {
					answer.add(cand);
				}
			}
			Collections.sort(answer);
			for (int i = 0; i < answer.size(); i++) {
				bw.write(String.valueOf(answer.get(i)));
				if (i != answer.size() - 1) {bw.write(" ");}
			}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}