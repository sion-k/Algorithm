package baekjoon.p01931;

import java.io.*;
import java.util.*;

public class Main {
	private static Meeting[] ms;

	private static class Meeting implements Comparable<Meeting> {
		public int start;
		public int end;

		public Meeting(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public int compareTo(Meeting other) {
			int diff = end - other.end;
			return diff == 0 ? start - other.start : diff;
		}

		@Override
		public String toString() {
			return start + " " + end;
		}
	}

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int c = Integer.parseInt(br.readLine());
			ms = new Meeting[c];

			for (int l = 0; l < c; l++) {
				String[] t = br.readLine().split(" ");
				ms[l] = new Meeting(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
			}
			br.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
		Arrays.sort(ms);
		int picked = 0;
		int last = 0;
		for (int i = 0; i < ms.length; i++) {
			if (ms[i].start >= last) {
				picked++;
				last = ms[i].end;
			}
		}
		System.out.println(picked);
	}
}
