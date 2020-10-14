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

	static int N; // ������ ����, ������ ��ȣ�� [1, N]�̴�
	static ArrayList<ArrayList<Pair>> adj; // ���� ����Ʈ ��� ǥ��
	static final int INF = 500000001;

	// ������ src�� ���� �ִ� ��� ���̸� ���� �迭�� ��ȯ�Ѵ�
	static int[] dijkstra(int src) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);// � ��ε� �̺��� Ŭ �� ����
		dist[src] = 0;// src �ڽ������� �ִ� ��� ���̴� 0
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		while(!pq.isEmpty()) {
			Pair p = pq.poll(); int here = p.num; int cost = p.cost;
			if(dist[here] < cost) {continue;}
			for (int i = 0; i < adj.get(here).size(); i++) {
				int there = adj.get(here).get(i).num;
				int nextDist = cost + adj.get(here).get(i).cost;
				// ������ �߰��� �ͺ��� �� ª�� ��θ� �߰� �� ���
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
			adj.add(null); // 0��° ������ ����
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
				adj.get(from).add(new Pair(to, weight));// ����� ����
				adj.get(to).add(new Pair(from, weight));// ����ġ�� [1, 1000]
			}

			ArrayList<Integer> answer = new ArrayList<>();
			int[] startTo = dijkstra(s); // ���������� ���ͽ�Ʈ��
			int gh = dijkstra(g)[h]; // g-h���� �ִ� �Ÿ�
			for (int i = 0; i < T; i++) {
				int cand = Integer.parseInt(br.readLine());
				// ���ʿ� �ĺ����� ������ �� ���� ��� ����
				if (startTo[cand] >= INF) {continue;}
				int[] candTo = dijkstra(cand); // �ĺ������� ���ͽ�Ʈ��
				// g-h�� �̵��� �� �ĺ������� �ִ� ��� ����
				int ghCand = startTo[g] + gh + candTo[h];
				// h-g�� �̵��� �� �ĺ������� �ִ� ��� ����
				int hgCand = startTo[h] + gh + candTo[g];
				// ���� ��� �ϳ��� �ĺ��������� �ִ� ��ο� ���ƾ� �ĺ��� �� �� �ִ�
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