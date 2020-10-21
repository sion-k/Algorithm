package baekjoon.p01854;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}

public class Main {
	static int N;
	static int K;
	static ArrayList<ArrayList<Pair>> adj;

	static ArrayList<ArrayList<Integer>> dijkstra(int src) {
		ArrayList<ArrayList<Integer>> dist =
		new ArrayList<ArrayList<Integer>>(N + 1);
		dist.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {dist.add(new ArrayList<>(K));}
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		dist.get(src).add(0);
		pq.offer(new Pair(src, 0));
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			// 정점을 방문하고 인접 정점을 검사
			for (Pair edge : adj.get(here)) {
				int there = edge.num;
				int nextDist = cost + edge.cost;
				int size = dist.get(there).size();
				if (size < K) {
					dist.get(there).add(nextDist);
					System.out.println("there : " + dist.get(there));
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Pair>>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {adj.add(new ArrayList<>());}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(start).add(new Pair(end, weight));
		}
		 ArrayList<ArrayList<Integer>> dist = dijkstra(1);
		 for (int i = 1; i < dist.size(); i++) {
			 ArrayList<Integer> j = dist.get(i);
			 Collections.sort(j);
			 if (K <= j.size()) {
				 bw.write(String.valueOf(j.get(K - 1)));
			 } else {
				 bw.write("-1");
			 }
			 bw.newLine();
		 }
		 bw.close();
	}

}