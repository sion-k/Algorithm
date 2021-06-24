import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	static int N; // 정점의 개수, 정점의 번호는 [1, N]이다
	static ArrayList<ArrayList<Pair>> adj;
	
	// 시작점 src로 부터 최단 경로 길이를 담은 배열을 반환한다
	static int[] dijkstra(int src) {
		int[] dist = new int[N + 1];
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
	static int solution(int A, int B) {
		// 1에서 최단 경로들
		int[] first = dijkstra(1);
		// N에서 최단 경로들
		int[] last = dijkstra(N);
		// A-B 최단 경로
		int ab = dijkstra(A)[B];
		if(ab == Integer.MAX_VALUE) {return -1;}
		// 1-A-B-N
		int sol1 = 0;
		if(first[A] == Integer.MAX_VALUE || last[B] == Integer.MAX_VALUE) {
			sol1 = Integer.MAX_VALUE;
		} else {
			sol1 = first[A] + ab + last[B];			
		}
		// 1-B-A-N
		int sol2 = 0;
		if(first[B] == Integer.MAX_VALUE || last[A] == Integer.MAX_VALUE) {
			sol2 = Integer.MAX_VALUE;
		} else {			
			sol2 = first[B] + ab + last[A];
		}
		int min = Math.min(sol1, sol2);
		if (min == Integer.MAX_VALUE) {
			return -1;
		} else {
			return min;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>(N);
		adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}

		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to   = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// 양방향 간선 추가
			adj.get(from).add(new Pair(to, weight));
			adj.get(to).add(new Pair(from, weight));
		}
		st = new StringTokenizer(br.readLine(), " ");
		br.close();
		// 무조건 지나야 하는 두 정점
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		System.out.println(solution(A, B));
		
	}

}
