package baekjoon.p2960;

import java.util.*;

public class Main {
	public int prime(int numbers, int k) {
		int[] array = new int[numbers - 1];
		for (int i = 2; i <= numbers; i++)
			array[i - 2] = i;
		int d = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0)
				continue;
			int temp = array[i];
			int j = 1;
			while (temp * j - 2 < array.length) {
				d++;
				if (d == k)
					return array[temp * j - 2];
				array[temp * j - 2] = 0;
				j++;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main a = new Main();
		System.out.println(a.prime(sc.nextInt(), sc.nextInt()));
		sc.close();
	}

}
