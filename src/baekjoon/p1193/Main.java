package baekjoon.p1193;

import java.util.*;

public class Main {
	public int sum(int n) {
		return n * (1 + n) / 2;
	}

	public int diagnol(int n) {
		int k = 1;
		while (sum(k) < n)
			k++;
		return k;
	}

	public String zig(int n) {
		int d = diagnol(n);
		int a;
		int b;
		if (d % 2 == 0) {
			a = 1;
			b = d;
			for (int i = 0; i < n - sum(diagnol(n) - 1) - 1; i++) {
				a++;
				b--;
			}
		} else {
			a = d;
			b = 1;
			for (int i = 0; i < n - sum(diagnol(n) - 1) - 1; i++) {
				a--;
				b++;
			}
		}
		return a + "/" + b;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		Main a = new Main();
		System.out.println(a.zig(input));
		sc.close();
	}

}
