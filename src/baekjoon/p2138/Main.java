package baekjoon.p2138;

import java.io.*;

public class Main {
	private static int size;
	private static StringBuilder input;
	private static StringBuilder goal;

	private static int solve(StringBuilder b) {
		int pushed = 0;
		for (int i = 1; i < size - 1; i++) {
			if (b.charAt(i - 1) != goal.charAt(i - 1)) {
				push(b, i);
				pushed++;
			}
		}

		return b.toString().equals(goal.toString()) ? pushed : -1;
	}

	private static void push(StringBuilder b, int i) {
		if (i == 0) {
			swit(b, 0);
			swit(b, 1);
		} else if (i == size - 1) {
			swit(b, size - 2);
			swit(b, size - 1);
		} else {
			swit(b, i - 1);
			swit(b, i);
			swit(b, i + 1);
		}
	}

	private static void swit(StringBuilder str, int i) {
		str.setCharAt(i, toggle(str.charAt(i)));
	}

	private static char toggle(char bulb) {
		return bulb == '0' ? '1' : '0';
	}

	public static void main(String[] args) {
		for (int i = 0; i < 0; i++) {
			System.out.print('0');
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			size = Integer.parseInt(br.readLine());
			input = new StringBuilder(br.readLine());
			goal = new StringBuilder(br.readLine());

		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder input2 = new StringBuilder(input.toString());
		push(input2, 0);
		StringBuilder input3 = new StringBuilder(input.toString());
		push(input3, size - 1);
		StringBuilder input4 = new StringBuilder(input.toString());
		push(input4, 0);
		push(input4, size - 1);

		int[] sol = new int[4];
		sol[0] = solve(input);

		sol[1] = solve(input2);
		sol[1] += (sol[1] == -1 ? 0 : 1);

		sol[2] = solve(input3);
		sol[2] += (sol[2] == -1 ? 0 : 1);

		sol[3] = solve(input4);
		sol[3] += (sol[3] == -1 ? 0 : 2);

		int minPushed = 100001;
		for (int i = 0; i < 4; i++) {
			if (sol[i] != -1)
				minPushed = Math.min(minPushed, sol[i]);
		}
		if (minPushed == 100001) {
			minPushed = -1;
		}
		System.out.println(minPushed);
	}
}
