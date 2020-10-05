package baekjoon.p01504;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Pair implements Comparable<Pair> {
		int num; int cost;
		public Pair(int n, int c) {num = n; cost = c;}
		public int compareTo(Pair o) {
			return cost - o.cost;
		}
	}
	
	static int V; // 정점의 개수, 정점의 번호는 [0, V)이다
	static ArrayList<ArrayList<Pair>> adj;
	
	// 시작점 src로 부터 최단 경로 길이를 담은 배열을 반환한다
	static int[] dijkstra(int src) {
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>(V);
		adj.add(new ArrayList<>());
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
			adj.set(i, new ArrayList<>());
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(from).add(new Pair(to, weight));
		}
		
		br.close();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] dist = dijkstra(K);
		for (int i = 1; i <= V; i++) {
			if (dist[i] != Integer.MAX_VALUE) {
				bw.write(String.valueOf(dist[i])); bw.newLine();
			} else {
				bw.write("INF"); bw.newLine();
			}
		}
		bw.close();
	}

}