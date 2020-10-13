package baekjoon.p09019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] BOOKED;
	static StringBuilder[] COMMAND;
	static int GOAL;
	static final String[] DSLR = { "D", "S", "L", "R" };
	// [0, 4) DSLR
	static int calc(int com, int dec) {
		switch(com) {
		case 0:
			return (dec * 2) % 10000;
		case 1:
			if (dec - 1 < 0) {return 9999;}
			return dec - 1;
		case 2:
			return (dec % 1000) * 10 + dec / 1000;
		case 3:
			return (dec % 10) * 1000 + dec / 10;
		}
		return -1;
	}

	static String BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start); BOOKED[start] = true;
		COMMAND[start] = new StringBuilder();

		while (!q.isEmpty()) {
			int here = q.poll();
			for (int com = 0; com < 4; com++) {
				int next = calc(com, here);
				if (!BOOKED[next]) {
					q.offer(next); BOOKED[next] = true;
					COMMAND[next] = new StringBuilder(COMMAND[here]);
					COMMAND[next].append(DSLR[com]);
					if (next == GOAL) {return COMMAND[next].toString();}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			BOOKED = new boolean[10000];
			COMMAND = new StringBuilder[10000];
			int start = Integer.parseInt(st.nextToken());
			GOAL = Integer.parseInt(st.nextToken());
			bw.write(BFS(start));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}